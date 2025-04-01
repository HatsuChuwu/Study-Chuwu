package com.example.example2;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    int[] imgs = getImgs();
    int position = 0;
    float x1 = 0.0f;
    float x2 = 0.0f;
    ImageView bigImage;
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

        bigImage = findViewById(R.id.bigImage);
        bigImage.setClickable(true);
        bigImage.setImageResource(imgs[position]);
        bigImage.setOnTouchListener(this);


    }

    public int[] getImgs() {
        int[] imgs = {R.drawable.img000, R.drawable.img001, R.drawable.img002, R.drawable.img003, R.drawable.img004,
                R.drawable.img005, R.drawable.img006, R.drawable.img007, R.drawable.img008, R.drawable.img009,
                R.drawable.img010, R.drawable.img011, R.drawable.img012, R.drawable.img013, R.drawable.img014,
                R.drawable.img015, R.drawable.img016, R.drawable.img017, R.drawable.img018, R.drawable.img019,
                R.drawable.img020, R.drawable.img021, R.drawable.img022, R.drawable.img023, R.drawable.img024,
                R.drawable.img025, R.drawable.img026, R.drawable.img027, R.drawable.img028, R.drawable.img029,
                R.drawable.img030, R.drawable.img031, R.drawable.img032};

        return imgs;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                 x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                 x2 = event.getX();
                float w = x2 - x1;

                if (w > 0) {
                    viewPrePhoto(); //往右

                } else if (w < 0) {
                    viewNextPhono();//往左
                }

                break;
        }
        return false;
    }

    private void viewNextPhono() {
        if (position == imgs.length - 1) {
            Toast.makeText(this,"已经是最后一张",Toast.LENGTH_LONG).show();
        }else{
            position++;
            bigImage.setImageResource(imgs[position]);
        }

    }

    private void viewPrePhoto() {

        if (position == 0) {
            Toast.makeText(this,"已经是第一张图片",Toast.LENGTH_LONG).show();
        }else{
            position--;
            bigImage.setImageResource(imgs[position]);
        }

    }
}