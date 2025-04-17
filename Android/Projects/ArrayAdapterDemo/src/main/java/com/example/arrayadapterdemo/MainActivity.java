package com.example.arrayadapterdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * 水果列表展示主界面
 * 使用ListView和自定义ArrayAdapter实现水果列表的展示
 * 实现了图片和文字的组合展示，并应用了ViewHolder模式优化性能
 *
 * 语法说明：
 * - public修饰符：类可以被其他包的类访问
 *   示例：public class MainActivity { }
 * - extends关键字：表示继承自AppCompatActivity基类，获得Activity的所有特性
 *   示例：public class MainActivity extends AppCompatActivity { }
 * - class关键字：声明这是一个类定义
 *   示例：public class ClassName { }
 * - @Override注解：表示重写父类方法
 *   示例：@Override
 *         protected void onCreate(Bundle savedInstanceState) { }
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化ListView并设置自定义适配器
        // 使用ArrayList作为数据源，支持动态添加和删除数据
        ListView listView = findViewById(R.id.listView);
        ArrayList<Fruit> fruitList = getFruitList();
        FruitArrayAdapter adapter = new FruitArrayAdapter(this, fruitList);
        listView.setAdapter(adapter);
    }

    /**
     * 生成水果数据列表
     * @return 包含水果信息的ArrayList集合
     * 
     * 语法说明：
     * - private修饰符：方法仅在当前类内部可访问
     *   示例：private void methodName() { }
     * - ArrayList<Fruit>：使用泛型约束列表元素类型为Fruit
     *   示例：ArrayList<String> stringList = new ArrayList<>();
     *   示例：ArrayList<Integer> numberList = new ArrayList<>();
     * - 方法返回值：在方法声明时指定返回类型
     *   示例：private ArrayList<Fruit> getFruitList() { return new ArrayList<>(); }
     */
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

    /**
     * 水果数据模型类
     * 使用final修饰成员变量确保数据不可变性
     * 
     * 语法说明：
     * - public修饰符：内部类可被外部访问
     *   示例：public class InnerClass { }
     * - static关键字：静态内部类，不依赖外部类实例
     *   示例：public static class StaticInnerClass { }
     * - class关键字：声明这是一个类定义
     *   示例：class SimpleClass { }
     * - final关键字：修饰变量使其不可变
     *   示例：private final int value = 0;
     *   示例：private final String text = "固定文本";
     */
    public static class Fruit {
        private final int imageId;  // 水果图片资源ID
        private final String name;  // 水果名称

        public Fruit(int imageId, String name) {
            this.imageId = imageId;
            this.name = name;
        }

        public int getImageId() {
            return imageId;
        }

        public String getName() {
            return name;
        }
    }
    
    /**
     * 自定义ArrayAdapter实现
     * 用于高效展示水果列表项，包含图片和文字
     * 实现了View复用机制，优化ListView性能
     * 
     * 语法说明：
     * - private修饰符：内部类仅在当前类中可访问
     *   示例：private class PrivateInnerClass { }
     * - static关键字：静态内部类，不持有外部类引用
     *   示例：private static class StaticAdapter { }
     * - extends ArrayAdapter<Fruit>：继承泛型类ArrayAdapter，指定数据类型为Fruit
     *   示例：class CustomAdapter extends ArrayAdapter<DataType> { }
     *   示例：class StringAdapter extends ArrayAdapter<String> { }
     */
    private static class FruitArrayAdapter extends ArrayAdapter<Fruit> {
        private final ArrayList<Fruit> fruitList;
        private final Context context;

        public FruitArrayAdapter(Context context, ArrayList<Fruit> fruitList) {
            super(context, R.layout.list_item, fruitList);
            this.fruitList = fruitList;
            this.context = context;
        }

        /**
         * 重写父类的getView方法，自定义列表项的显示样式
         * 
         * 语法说明：
         * - @NonNull注解：表示返回值不能为null
         *   示例：@NonNull
         *         public String getText() { return "非空文本"; }
         * - @Override注解：表示重写父类方法
         *   示例：@Override
         *         protected void onResume() { super.onResume(); }
         * - public修饰符：方法可被其他类访问
         *   示例：public void publicMethod() { }
         * - @Nullable注解：参数convertView可以为null
         *   示例：public void processData(@Nullable String data) { }
         */
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // 实现View复用机制，避免重复创建View对象
            View listItem = convertView;
            if (listItem == null) {
                listItem = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            }
            
            // 获取当前位置的水果数据
            Fruit currentFruit = fruitList.get(position);
            
            // 设置水果图片
            ImageView imageView = listItem.findViewById(R.id.fruit_image);
            imageView.setImageResource(currentFruit.getImageId());
            
            // 设置水果名称
            TextView textView = listItem.findViewById(R.id.fruit_name);
            textView.setText(currentFruit.getName());
            
            return listItem;
        }
    }
}