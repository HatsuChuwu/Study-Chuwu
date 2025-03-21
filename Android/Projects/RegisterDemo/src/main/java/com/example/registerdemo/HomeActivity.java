package com.example.registerdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 主页活动类，负责展示应用的主界面，同时管理滑动菜单的显示与隐藏。
 */
public class HomeActivity extends AppCompatActivity {

    // 定义滑动菜单视图对象，用于存储从布局文件加载的滑动菜单视图
    private View slideMenu;

    /**
     * 当活动创建时调用此方法，进行界面初始化和资源加载。
     * @param savedInstanceState 如果活动是重新创建的，则包含之前保存的状态；否则为 null。
     */
    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 调用父类的 onCreate 方法，确保活动的正常创建流程
        super.onCreate(savedInstanceState);
        // 设置当前活动要显示的布局文件为 activity_home.xml
        setContentView(R.layout.activity_home);

        // 初始化滑动菜单
        // 使用布局加载器将 slide_menu.xml 布局文件加载为一个视图对象
        slideMenu = getLayoutInflater().inflate(R.layout.slide_menu, null);
        // 将加载好的滑动菜单视图添加到当前窗口的根视图中，使其成为界面的一部分
        ((ViewGroup) getWindow().getDecorView()).addView(slideMenu);
        // 初始时将滑动菜单设置为不可见，避免刚进入主页就显示菜单
        slideMenu.setVisibility(View.GONE);

        // 设置点击菜单外区域关闭菜单
        // 为滑动菜单视图设置点击监听器，当点击菜单外区域时触发关闭菜单的操作
        slideMenu.setOnClickListener(v -> hideMenu());

        // 防止点击菜单内容区域关闭菜单
        // 为菜单内容的容器视图设置点击监听器，将点击事件拦截，避免传递到父视图（即滑动菜单视图）
        // 这样点击菜单内容时不会触发关闭菜单的操作
        slideMenu.findViewById(R.id.menu_container).setOnClickListener(View::performClick);
    }

    /**
     * 隐藏滑动菜单的方法，同时应用上滑动画效果。
     */
    private void hideMenu() {
        // 从资源文件中加载上滑动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        // 为动画设置监听器，用于监听动画的开始、结束和重复事件
        animation.setAnimationListener(new Animation.AnimationListener() {
            /**
             * 动画开始时调用此方法，这里不做任何处理。
             * @param animation 当前正在执行的动画对象
             */
            @Override
            public void onAnimationStart(Animation animation) {}

            /**
             * 动画结束时调用此方法，将滑动菜单的可见性设置为不可见。
             * @param animation 当前正在执行的动画对象
             */
            @Override
            public void onAnimationEnd(Animation animation) {
                // 当动画结束时，将滑动菜单设置为不可见，完成隐藏操作
                slideMenu.setVisibility(View.GONE);
            }

            /**
             * 动画重复时调用此方法，这里不做任何处理。
             * @param animation 当前正在执行的动画对象
             */
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        // 为菜单内容的容器视图应用上滑动画，实现菜单隐藏的动画效果
        slideMenu.findViewById(R.id.menu_container).startAnimation(animation);
    }
}