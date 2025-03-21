package com.example.example2_8;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //将复选框id的值存放在数组里，方便编程遍历复选框
    int[] chk_id = {R.id.chk1, R.id.chk2, R.id.chk3, R.id.chk4
            , R.id.chk5, R.id.chk6, R.id.chk7, R.id.chk8, R.id.chk9};
    TextView tvFood;
Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFood = this.findViewById(R.id.tvFood);
        btnBuy = findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(view -> {
           //存放选中餐饮的字符串
            StringBuilder msg = new StringBuilder();
            for (int id : chk_id) {
                CheckBox chk = findViewById(id);
                if (chk.isChecked())
                    msg.append(chk.getText()).append("\t\t\t").append(" x1 ").append("\n");
            }
            //显示选中的餐饮
            tvFood.setText(msg.toString());
        });
    }
}
