package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener { // 实现 View.OnClickListener 接口

    private TextView tvText;
    private boolean isOriginalText = true; // 添加状态变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = findViewById(R.id.tvText);
        Button btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(this); // 设置监听器为当前 Activity 实例
    }

    @Override
    public void onClick(View v) { // 实现 onClick 方法
        if (v.getId() == R.id.btnClick) { // 检查点击的是否是按钮
            if (isOriginalText) {
                tvText.setText("实现屏幕类监听器接口方法");
                isOriginalText = false;
            } else {
                tvText.setText("你好！");
                isOriginalText = true;
            }
        }
    }
}