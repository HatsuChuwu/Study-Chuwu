package com.example.fruitbaseadapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化ListView和数据适配器
        ListView listView = findViewById(R.id.listView);
        ArrayList<Fruit> fruitList = getFruitList();
        FruitAdapter adapter = new FruitAdapter(this, fruitList);
        listView.setAdapter(adapter);
    }

    // 生成测试数据
    private ArrayList<Fruit> getFruitList() {
        ArrayList<Fruit> fruits = new ArrayList<>();
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

    // 内部类：自定义BaseAdapter
    private static class FruitAdapter extends BaseAdapter {
        private final ArrayList<Fruit> fruitList;
        private final MainActivity context;

        public FruitAdapter(MainActivity context, ArrayList<Fruit> fruitList) {
            this.fruitList = fruitList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return fruitList.size();
        }

        @Override
        public Fruit getItem(int position) {
            return fruitList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(context, R.layout.list_item, null);
            Fruit f = fruitList.get(position);
            ImageView img = convertView.findViewById(R.id.fruit_image);
            img.setImageResource(f.getImageId());
            TextView text = convertView.findViewById(R.id.fruit_name);
            text.setText(f.getName());
            return convertView;
        }
    }
}