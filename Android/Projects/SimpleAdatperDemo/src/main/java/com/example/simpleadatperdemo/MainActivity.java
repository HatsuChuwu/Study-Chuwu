package com.example.simpleadatperdemo;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<Map<String, Object>> dataList;

    // 书名数组
    private final String[] bookNames = {
            "天空在脚下","活了100万次的猫","帕西爷爷有办法","丢饭团的笑婆子",
            "獾的礼物","魔奇魔奇树","市场街最后一站","打架的艺术",
            "明天要远足","妖怪山","我有友情要出租","月光男孩","小真的长头发","不可思议的旅程","一粒种子的旅行","脱不下来啦","小金鱼逃走了",
            "海底100层的房子","蝴蝶·豌豆花","会长鱼的树"
    };

    // 作者数组
    private final String[] authors = {
            "[美]埃米莉.阿诺德.麦卡利","[日]佐野洋子","[英]尼克.巴特沃斯","[美]阿琳·莫赛",
            "苏珊.华莱","[日]齐藤隆介","[美]马特·德拉培尼亚","[意]大卫·卡利",
            "方素珍","彭懿","方素珍","依卜·斯旁·奥尔森","[日]高楼方子","[美]艾伦.贝克尔","【瑞士】安娜·克罗萨","[日]吉竹伸价","[日]五味太郞",
            "[日]岩井俊雄","金波","郑春华"
    };

    // 图片资源ID数组
    private final int[] imageIds = {
            R.drawable.list000,
            R.drawable.list001,
            R.drawable.list002,
            R.drawable.list003,
            R.drawable.list004,
            R.drawable.list005,
            R.drawable.list006,
            R.drawable.list007,
            R.drawable.list008,
            R.drawable.list009,
            R.drawable.list010,
            R.drawable.list011,
            R.drawable.list012,
            R.drawable.list013,
            R.drawable.list014,
            R.drawable.list015,
            R.drawable.list016,
            R.drawable.list017,
            R.drawable.list018,
            R.drawable.list019
    };

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

        // 初始化ListView
        ListView listView = findViewById(R.id.listView);
        
        // 准备数据
        initData();
        
        // 创建SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                dataList,
                R.layout.item_layout,
                new String[]{"image", "name", "author"},
                new int[]{R.id.imageViewFruit, R.id.textViewFruit, R.id.textViewAuthor}
        );
        
        // 设置适配器
        listView.setAdapter(adapter);
    }

    // 初始化数据方法
    private void initData() {
        dataList = new ArrayList<>();
        
        for (int i = 0; i < bookNames.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imageIds[i]);
            map.put("name", bookNames[i]);
            map.put("author", authors[i]);
            dataList.add(map);
        }
    }
}