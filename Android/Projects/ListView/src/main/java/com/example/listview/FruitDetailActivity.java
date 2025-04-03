package com.example.listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class FruitDetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerTabStrip pagerTabStrip;
    private Fruit fruit;
    private List<View> viewList;
    private List<String> selectedFruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_detail);

        // 获取传递过来的水果数据
        int position = getIntent().getIntExtra("position", 0);
        fruit = FruitData.getFruitList().get(position);

        // 初始化ViewPager和PagerTabStrip
        viewPager = findViewById(R.id.viewPager);
        pagerTabStrip = findViewById(R.id.pagerTabStrip);
        pagerTabStrip.setTabIndicatorColorResource(R.color.purple_500);
        
        // 初始化选中水果列表
        selectedFruits = new ArrayList<>();
        
        initViews();

        // 设置适配器
        MyPagerAdapter adapter = new MyPagerAdapter(viewList);
        viewPager.setAdapter(adapter);
    }

    private void initViews() {
        viewList = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        
        // 初始化详情页面
        View detailView = inflater.inflate(R.layout.fruit_detail, null);
        TextView nameTextView = detailView.findViewById(R.id.fruit_detail_name);
        ImageView imageView = detailView.findViewById(R.id.fruit_detail_image);
        TextView descriptionTextView = detailView.findViewById(R.id.fruit_detail_description);
        Button btnBackToList = detailView.findViewById(R.id.btn_back_to_list);
        
        nameTextView.setText(fruit.getName());
        imageView.setImageResource(fruit.getImageId());
        descriptionTextView.setText(fruit.getDescription());
        
        // 设置返回按钮点击事件
        btnBackToList.setOnClickListener(v -> finish());
        
        viewList.add(detailView);
        
        // 初始化评论页面
        View commentsView = inflater.inflate(R.layout.fruit_comments, null);
        TextView commentNameTextView = commentsView.findViewById(R.id.fruit_comment_name);
        LinearLayout commentsContainer = commentsView.findViewById(R.id.comments_container);
        EditText editComment = commentsView.findViewById(R.id.edit_comment);
        Button btnSendComment = commentsView.findViewById(R.id.btn_send_comment);
        Button btnBackToListFromComments = commentsView.findViewById(R.id.btn_back_to_list);
        
        commentNameTextView.setText(fruit.getName());
        
        // 添加评论
        String[] comments = fruit.getComments();
        for (String comment : comments) {
            View commentView = inflater.inflate(R.layout.comment_item, null);
            TextView commentTextView = commentView.findViewById(R.id.comment_text);
            commentTextView.setText(comment);
            commentsContainer.addView(commentView);
        }
        
        // 设置发送评论按钮点击事件
        btnSendComment.setOnClickListener(v -> {
            String commentText = editComment.getText().toString().trim();
            if (!commentText.isEmpty()) {
                View commentView = inflater.inflate(R.layout.comment_item, null);
                TextView commentTextView = commentView.findViewById(R.id.comment_text);
                commentTextView.setText(commentText);
                commentsContainer.addView(commentView, 0); // 添加到顶部
                editComment.setText(""); // 清空输入框
                Toast.makeText(FruitDetailActivity.this, "评论已发送", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(FruitDetailActivity.this, "请输入评论内容", Toast.LENGTH_SHORT).show();
            }
        });
        
        // 设置返回按钮点击事件
        btnBackToListFromComments.setOnClickListener(v -> finish());
        
        viewList.add(commentsView);
        
        // 初始化订购页面
        View orderView = inflater.inflate(R.layout.fruit_order, null);
        GridLayout checkboxContainer = orderView.findViewById(R.id.fruit_checkbox_container);
        Button btnSubmitOrder = orderView.findViewById(R.id.btn_submit_order);
        TextView selectedFruitsText = orderView.findViewById(R.id.selected_fruits_text);
        Button btnBackToListFromOrder = orderView.findViewById(R.id.btn_back_to_list);
        
        // 添加水果复选框
        List<Fruit> fruitList = FruitData.getFruitList();
        for (Fruit f : fruitList) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(f.getName());
            checkBox.setTextSize(14);
            checkBox.setPadding(5, 5, 5, 5);
            
            // 设置复选框的布局参数，适应GridLayout
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = GridLayout.LayoutParams.WRAP_CONTENT;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.setMargins(5, 5, 5, 5);
            checkBox.setLayoutParams(params);
            
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (!selectedFruits.contains(f.getName())) {
                        selectedFruits.add(f.getName());
                    }
                } else {
                    selectedFruits.remove(f.getName());
                }
                
                // 更新已选择的水果列表
                updateSelectedFruits(selectedFruitsText);
            });
            
            checkboxContainer.addView(checkBox);
        }
        
        // 设置提交订单按钮点击事件
        btnSubmitOrder.setOnClickListener(v -> {
            if (selectedFruits.isEmpty()) {
                Toast.makeText(FruitDetailActivity.this, "请至少选择一种水果", Toast.LENGTH_SHORT).show();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("您已订购以下水果：\n");
                for (String fruitName : selectedFruits) {
                    sb.append(fruitName).append("\n");
                }
                Toast.makeText(FruitDetailActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
                
                // 清空选择
                selectedFruits.clear();
                for (int i = 0; i < checkboxContainer.getChildCount(); i++) {
                    View view = checkboxContainer.getChildAt(i);
                    if (view instanceof CheckBox) {
                        ((CheckBox) view).setChecked(false);
                    }
                }
                updateSelectedFruits(selectedFruitsText);
            }
        });
        
        // 设置返回按钮点击事件
        btnBackToListFromOrder.setOnClickListener(v -> finish());
        
        viewList.add(orderView);
    }

    // 更新已选择的水果列表
    private void updateSelectedFruits(TextView textView) {
        if (selectedFruits.isEmpty()) {
            textView.setText("暂无选择");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String fruitName : selectedFruits) {
                sb.append(fruitName).append("\n");
            }
            textView.setText(sb.toString());
        }
    }
    
    // ViewPager适配器
    private class MyPagerAdapter extends PagerAdapter {
        private List<View> viewList;
        private final String[] titles = {"水果介绍", "水果评价", "水果订购"};

        public MyPagerAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull android.view.ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(@NonNull android.view.ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
        
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}