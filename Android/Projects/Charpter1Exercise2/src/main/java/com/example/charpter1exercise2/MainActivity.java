package com.example.charpter1exercise2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // 用于显示计算结果的文本视图
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 初始化界面控件
        Button calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);

        // 设置按钮点击事件，点击时调用calculateSum方法进行计算
        calculateButton.setOnClickListener(v -> calculateSum());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * 计算1到100的累加和
     * 使用循环遍历1-100的数字并累加
     * 计算结果同时输出到日志和界面上
     */
    private void calculateSum() {
        int sum = 0;  // 初始化累加和变量
        // 循环计算1到100的累加和
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        String result = "1-100的累加和为：" + sum;
        Log.v("result", sum + "");  // 将结果输出到日志
        resultTextView.setText(result);  // 将结果显示到界面上
    }
}