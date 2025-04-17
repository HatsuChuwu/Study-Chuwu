package com.example.drawingarrayadapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity类 - 主界面活动
 * 功能：展示一个包含图书信息的ListView列表
 * 实现：使用自定义的ArrayAdapter来绑定数据和视图
 */
public class MainActivity extends AppCompatActivity {

    private List<Book> bookList;

    // 书名数组 - 存储所有图书的名称
    private final String[] bookNames = {
            "天空在脚下","活了100万次的猫","帕西爷爷有办法","丢饭团的笑婆子",
            "獾的礼物","魔奇魔奇树","市场街最后一站","打架的艺术",
            "明天要远足","妖怪山","我有友情要出租","月光男孩","小真的长头发","不可思议的旅程","一粒种子的旅行","脱不下来啦","小金鱼逃走了",
            "海底100层的房子","蝴蝶·豌豆花","会长鱼的树"
    };

    // 作者数组 - 存储所有图书的作者信息
    private final String[] authors = {
            "[美]埃米莉.阿诺德.麦卡利","[日]佐野洋子","[英]尼克.巴特沃斯","[美]阿琳·莫赛",
            "苏珊.华莱","[日]齐藤隆介","[美]马特·德拉培尼亚","[意]大卫·卡利",
            "方素珍","彭懿","方素珍","依卜·斯旁·奥尔森","[日]高楼方子","[美]艾伦.贝克尔","【瑞士】安娜·克罗萨","[日]吉竹伸价","[日]五味太郞",
            "[日]岩井俊雄","金波","郑春华"
    };

    // 图片资源ID数组 - 存储所有图书封面图片的资源ID
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

    /**
     * Activity创建时的初始化方法
     * 功能：
     * 1. 设置布局文件
     * 2. 初始化ListView
     * 3. 创建并设置适配器
     */
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
        // 创建自定义的BookAdapter
        BookAdapter adapter = new BookAdapter(this, R.layout.item_layout, bookList);
        // 设置适配器
        listView.setAdapter(adapter);
    }

    /**
     * 初始化数据方法
     * 功能：创建Book对象列表，将书名、作者和图片资源组合成Book对象
     * 
     * 使用示例：
     * // 在Activity或Fragment中调用
     * private List<Book> bookList;
     * 
     * private void setupBookList() {
     *     initData(); // 初始化数据
     *     BookAdapter adapter = new BookAdapter(this, R.layout.item_layout, bookList);
     *     listView.setAdapter(adapter);
     * }
     */
    private void initData() {
        bookList = new ArrayList<>();

        for (int i = 0; i < bookNames.length; i++) {
            Book book = new Book(imageIds[i], bookNames[i], authors[i]);
            bookList.add(book);
        }
    }

    /**
     * Book类 - 图书信息数据模型
     * 属性：
     * - imageId：图书封面图片的资源ID
     * - name：图书名称
     * - author：图书作者
     * 
     * 使用示例：
     * // 创建Book对象
     * Book book = new Book(R.drawable.book_cover, "深入理解计算机系统", "Randal E. Bryant");
     * 
     * // 获取Book对象的属性
     * ImageView coverImage = findViewById(R.id.book_cover);
     * coverImage.setImageResource(book.getImageId()); // 设置封面图片
     * 
     * TextView titleText = findViewById(R.id.book_title);
     * titleText.setText(book.getName()); // 设置书名
     * 
     * TextView authorText = findViewById(R.id.book_author);
     * authorText.setText(book.getAuthor()); // 设置作者名
     */
    public static class Book {
        private final int imageId;
        private final String name;
        private final String author;

        public Book(int imageId, String name, String author) {
            this.imageId = imageId;
            this.name = name;
            this.author = author;
        }

        public int getImageId() {
            return imageId;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }
    }

    /**
     * BookAdapter类 - 自定义的ArrayAdapter
     * 功能：将Book对象的数据绑定到ListView的每个列表项视图上
     * 实现：使用ViewHolder模式优化列表性能
     * 
     * 使用示例：
     * // 准备数据
     * List<Book> bookList = new ArrayList<>();
     * bookList.add(new Book(R.drawable.book1, "书名1", "作者1"));
     * bookList.add(new Book(R.drawable.book2, "书名2", "作者2"));
     * 
     * // 创建适配器
     * ListView listView = findViewById(R.id.listView);
     * BookAdapter adapter = new BookAdapter(this, R.layout.item_layout, bookList);
     * listView.setAdapter(adapter);
     * 
     * // 更新数据
     * bookList.add(new Book(R.drawable.book3, "新书", "新作者"));
     * adapter.notifyDataSetChanged();
     */
    private static class BookAdapter extends ArrayAdapter<Book> {
        private final int resourceId;

        public BookAdapter(Context context, int resourceId, List<Book> objects) {
            super(context, resourceId, objects);
            this.resourceId = resourceId;
        }

        /**
         * getView方法 - 创建或重用列表项视图
         * 参数：
         * - position：当前项在数据集中的位置
         * - convertView：重用的视图对象
         * - parent：父视图组
         * 
         * 实现：
         * 1. 使用ViewHolder模式缓存视图引用
         * 2. 如果convertView为null，创建新视图并设置ViewHolder
         * 3. 否则重用convertView并获取其ViewHolder
         * 4. 将数据绑定到视图上
         */
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Book book = getItem(position);
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

                holder = new ViewHolder();
                holder.imageView = convertView.findViewById(R.id.imageViewFruit);
                holder.nameTextView = convertView.findViewById(R.id.textViewFruit);
                holder.authorTextView = convertView.findViewById(R.id.textViewAuthor);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (book != null) {
                holder.imageView.setImageResource(book.getImageId());
                holder.nameTextView.setText(book.getName());
                holder.authorTextView.setText(book.getAuthor());
            }

            return convertView;
        }

        /**
         * ViewHolder类 - 视图持有者
         * 功能：缓存列表项中的视图引用，避免重复调用findViewById
         * 属性：
         * - imageView：显示图书封面
         * - nameTextView：显示图书名称
         * - authorTextView：显示作者名称
         */
        private static class ViewHolder {
            ImageView imageView;
            TextView nameTextView;
            TextView authorTextView;
        }
    }
}