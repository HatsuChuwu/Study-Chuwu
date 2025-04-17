package com.example.fruitbaseadapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

/**
 * 水果列表展示界面
 * 功能：展示水果列表，支持单选、全选、反选等操作
 * 实现：使用ListView + BaseAdapter的经典列表实现方案
 * 语法要点：
 * 1. 静态内部类（static class）：用于定义数据模型，避免内存泄漏
 * 2. BaseAdapter使用：通过继承实现自定义列表适配器
 * 3. Lambda表达式：简化事件监听器的实现，如 view -> { ... }
 * 4. ArrayList操作：add()添加元素，get()获取元素
 * 5. findViewById：获取布局中的控件引用
 * 6. 视图绑定：通过Adapter将数据与ListView的每个项关联
 */
public class MainActivity extends AppCompatActivity {
    /**
     * 内部类：水果数据模型
     * 用于存储水果的基本信息（图片、名称）和选中状态
     */
    private static class Fruit {
        private final int imageId;  // 水果图片资源ID
        private final String name;  // 水果名称
        private boolean isSelected;  // 是否被选中

        /**
         * 构造函数
         * @param imageId 水果图片资源ID
         * @param name 水果名称
         */
        public Fruit(int imageId, String name) {
            this.imageId = imageId;
            this.name = name;
            this.isSelected = false;
        }

        public int getImageId() {
            return imageId;
        }

        public String getName() {
            return name;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

    private FruitAdapter adapter;  // 列表适配器
    private ArrayList<Fruit> fruitList;  // 水果数据列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化数据和适配器
        fruitList = getFruitList();  // 获取测试数据
        adapter = new FruitAdapter(this, fruitList);  // 创建适配器实例

        // 初始化ListView并设置适配器
        ListView listView = findViewById(R.id.listView);
        
        // 添加header视图：包含全选和反选按钮
        View headerView = getLayoutInflater().inflate(R.layout.list_header, listView, false);
        listView.addHeaderView(headerView);

        // 添加footer视图：包含提交按钮
        View footerView = getLayoutInflater().inflate(R.layout.list_footer, listView, false);
        listView.addFooterView(footerView);
        
        listView.setAdapter(adapter);
        
        // 设置header中全选按钮的点击事件
        // Lambda表达式语法：(参数) -> { 方法体 }
        // 等价于传统的匿名内部类写法：
        // new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) { ... }
        // }
        headerView.findViewById(R.id.btn_select_all).setOnClickListener(v -> {
            for (Fruit fruit : fruitList) {
                fruit.setSelected(true);  // 将所有水果设置为选中状态
            }
            adapter.notifyDataSetChanged();  // 通知适配器数据已更新
        });

        // 设置header中反选按钮的点击事件
        headerView.findViewById(R.id.btn_inverse_select).setOnClickListener(v -> {
            for (Fruit fruit : fruitList) {
                fruit.setSelected(!fruit.isSelected());  // 切换每个水果的选中状态
            }
            adapter.notifyDataSetChanged();
        });

        // 设置ListView项点击事件
        // Lambda表达式实现AdapterView.OnItemClickListener接口
        // 参数说明：
        // parent: ListView本身（AdapterView类型）
        // view: 被点击的列表项视图（View类型）
        // position: 被点击项的位置（包含header，从0开始）
        // id: 列表项的ID（由getItemId方法返回）
        // 
        // 传统匿名内部类写法：
        // listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //     @Override
        //     public void onItemClick(AdapterView<?> parent, View view, int position, long id) { ... }
        // });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // 由于有header，需要减1来获取正确的position
            int actualPosition = position - 1;
            // 确保点击的是列表项而不是header或footer
            if (actualPosition >= 0 && actualPosition < fruitList.size()) {
                Fruit fruit = fruitList.get(actualPosition);
                fruit.setSelected(!fruit.isSelected());  // 切换选中状态
                adapter.notifyDataSetChanged();
            }
        });

        // 设置footer中提交按钮的点击事件
        footerView.findViewById(R.id.btn_submit).setOnClickListener(v -> {
            // 统计选中的水果数量
            int selectedCount = 0;
            for (Fruit fruit : fruitList) {
                if (fruit.isSelected()) {
                    selectedCount++;
                }
            }
            // 根据选中数量显示不同的提示信息
            if (selectedCount == fruitList.size()) {
                Toast.makeText(this, "所有的水果都被选中了！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "您选中了" + selectedCount + "种水果！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 生成测试数据
     * @return 包含水果信息的ArrayList
     */
    private ArrayList<Fruit> getFruitList() {
        ArrayList<Fruit> fruits = new ArrayList<>();
        // 添加测试数据
        fruits.add(new Fruit(R.drawable.boluo, "菠萝"));
        fruits.add(new Fruit(R.drawable.caomei, "草莓"));
        fruits.add(new Fruit(R.drawable.chengzi, "橙子"));
        fruits.add(new Fruit(R.drawable.hamigua, "哈密瓜"));
        fruits.add(new Fruit(R.drawable.jinju, "金桔"));
        fruits.add(new Fruit(R.drawable.mangguo, "芒果"));
        fruits.add(new Fruit(R.drawable.mihoutao, "蜜猴桃"));
        fruits.add(new Fruit(R.drawable.putao, "葡萄"));
        fruits.add(new Fruit(R.drawable.xiangjiao, "香蕉"));
        fruits.add(new Fruit(R.drawable.youtao, "油桃"));
        fruits.add(new Fruit(R.drawable.youzi, "柚子"));
        return fruits;
    }

    /**
     * 内部类：自定义BaseAdapter
     * 用于将水果数据绑定到ListView的每个列表项
     * BaseAdapter用法说明：
     * 1. 继承BaseAdapter类并实现其抽象方法
     * 2. 必须重写的四个核心方法：
     *    - getCount(): 返回数据项的总数，决定ListView显示多少项
     *    - getItem(int): 返回指定位置的数据项，用于获取数据模型
     *    - getItemId(int): 返回指定位置的项ID，用于列表项的唯一标识
     *    - getView(int, View, ViewGroup): 返回列表项视图，这里完成数据与视图的绑定
     * 3. 建议使用ViewHolder模式优化性能（本例简化未使用）
     * 4. 数据更新后调用notifyDataSetChanged()刷新列表
     */
    private static class FruitAdapter extends BaseAdapter {
        private final ArrayList<Fruit> fruitList;
        private final MainActivity context;

        /**
         * 构造函数
         * @param context 上下文
         * @param fruitList 水果数据列表
         */
        public FruitAdapter(MainActivity context, ArrayList<Fruit> fruitList) {
            this.fruitList = fruitList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return fruitList.size();  // 返回列表项总数
        }

        @Override
        public Fruit getItem(int position) {
            return fruitList.get(position);  // 返回指定位置的水果对象
        }

        @Override
        public long getItemId(int position) {
            return position;  // 返回列表项ID，这里直接使用位置作为ID
        }

        /**
         * 获取指定位置的列表项视图
         * @param position 位置
         * @param convertView 复用的视图
         * @param parent 父视图
         * @return 列表项视图
         */
        @SuppressLint("ViewHolder")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 注意：这里简化了实现，没有使用ViewHolder模式
            // 在实际应用中，应该使用ViewHolder模式来提高性能
            convertView = View.inflate(context, R.layout.list_item, null);
            Fruit f = fruitList.get(position);
            
            // 设置水果图片
            ImageView img = convertView.findViewById(R.id.fruit_image);
            img.setImageResource(f.getImageId());
            
            // 设置水果名称
            TextView text = convertView.findViewById(R.id.fruit_name);
            text.setText(f.getName());
            
            // 设置选中状态
            CheckBox checkBox = convertView.findViewById(R.id.fruit_checkbox);
            checkBox.setChecked(f.isSelected());
            
            return convertView;
        }
    }
}