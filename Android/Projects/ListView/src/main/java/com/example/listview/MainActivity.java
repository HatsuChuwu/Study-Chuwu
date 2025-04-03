package com.example.listview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        // 初始化控件
        listView1 = findViewById(R.id.listView);
        
        // 获取水果数据
        // 水果数据
        SimpleAdapter adapter = getSimpleAdapter();

        // 设置适配器
        listView1.setAdapter(adapter);
        
        // 设置点击事件
        listView1.setOnItemClickListener((parent, view, position, id) -> {
            // 打开详情页面
            Intent intent = new Intent(MainActivity.this, FruitDetailActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        });
    }

    @NonNull
    private SimpleAdapter getSimpleAdapter() {
        List<Fruit> fruitList = FruitData.getFruitList();

        // 准备数据源
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (Fruit fruit : fruitList) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", fruit.getImageId());
            map.put("name", fruit.getName());
            dataList.add(map);
        }

        // 创建SimpleAdapter
        return new SimpleAdapter(this, dataList, R.layout.fruit_item,
                new String[]{"image", "name"},
                new int[]{R.id.fruit_image, R.id.fruit_name});
    }
}