package com.example.registerdemo;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * 主活动类，负责处理登录界面的逻辑，包括用户输入验证、登录操作、注册跳转、菜单显示等功能。
 */
public class MainActivity extends AppCompatActivity {

    // 定义用于输入昵称的文本输入框
    private TextInputEditText nicknameInput;
    // 定义用于输入密码的文本输入框
    private TextInputEditText passwordInput;
    // 定义用户协议勾选框
    private CheckBox agreementCheckbox;
    // 定义滑动菜单视图
    private View slideMenu;
    // 标记菜单是否可见
    private boolean isMenuVisible = false;
    // 定义用于输入找回密码账号的文本输入框
    private TextInputEditText recoverPasswordInput;
    // 定义用于显示找回密码结果的文本视图
    private TextView passwordResult;

    /**
     * 显示自定义的Toast消息。
     * @param message 要显示的消息内容
     */
    private void showCustomToast(String message) {
        // 获取布局加载器
        LayoutInflater inflater = getLayoutInflater();
        // 加载自定义Toast布局
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));
        // 获取Toast布局中的图片视图
        ImageView image = layout.findViewById(R.id.toast_image);
        // 设置图片资源
        image.setImageResource(R.drawable.bocchi);
        // 获取Toast布局中的文本视图
        TextView text = layout.findViewById(R.id.toast_text);
        // 设置文本内容
        text.setText(message);
        // 创建Toast对象
        Toast toast = new Toast(getApplicationContext());
        // 设置Toast显示时长
        toast.setDuration(Toast.LENGTH_SHORT);
        // 设置Toast显示的视图
        toast.setView(layout);
        // 显示Toast
        toast.show();
    }

    /**
     * 活动创建时调用的方法，进行视图初始化和事件绑定。
     * savedInstanceState 如果活动是重新创建的，则包含之前保存的状态
     */
    private static final String CHANNEL_ID = "password_recovery";
    private NotificationManager notificationManager;

    private void createNotificationChannel() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence name = "密码找回通知";
        String description = "用于显示找回的密码信息";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        notificationManager.createNotificationChannel(channel);
    }

    private void showPasswordNotification() {
        android.app.Notification.Builder builder;
        builder = new android.app.Notification.Builder(this, CHANNEL_ID);

        builder.setSmallIcon(R.drawable.key_icon)
                .setContentTitle("密码找回")
                .setContentText("密码是：" + "123");

        notificationManager.notify(1, builder.build());
    }

    @SuppressLint(value = {"InflateParams", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 请求通知权限（Android 13及以上版本需要）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 100);
        }
        

        createNotificationChannel();
        // 设置Toolbar
        // 从布局中获取Toolbar视图
        Toolbar toolbar = findViewById(R.id.toolbar);
        // 将Toolbar设置为活动的ActionBar
        setSupportActionBar(toolbar);
        // 隐藏ActionBar的标题显示
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // 初始化视图组件
        // 从布局中获取昵称输入框
        nicknameInput = findViewById(R.id.nicknameInput);
        // 从布局中获取密码输入框
        passwordInput = findViewById(R.id.passwordInput);
        // 从布局中获取用户协议勾选框
        agreementCheckbox = findViewById(R.id.agreementCheckbox);
        // 从布局中获取登录按钮
        Button loginButton = findViewById(R.id.loginButton);
        // 从布局中获取注册链接文本视图
        TextView registerLink = findViewById(R.id.registerLink);
        
        // 检查是否从注册页面传递了用户名
        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            // 如果有传递用户名，则自动填充到输入框
            nicknameInput.setText(username);
            // 将焦点设置到密码输入框
            passwordInput.requestFocus();
        }

        // 初始化滑动菜单
        // 加载滑动菜单布局
        slideMenu = getLayoutInflater().inflate(R.layout.slide_menu, null);
        // 将滑动菜单添加到窗口的根视图中
        ((ViewGroup) getWindow().getDecorView()).addView(slideMenu);
        // 初始时隐藏滑动菜单
        slideMenu.setVisibility(View.GONE);

        // 初始化找回密码组件
        // 从滑动菜单布局中获取找回密码输入框
        recoverPasswordInput = slideMenu.findViewById(R.id.recover_password_input);
        // 从滑动菜单布局中获取显示找回密码结果的文本视图
        passwordResult = slideMenu.findViewById(R.id.password_result);
        // 从滑动菜单布局中获取找回密码按钮
        Button recoverPasswordButton = slideMenu.findViewById(R.id.recover_password_button);

        // 设置找回密码按钮点击事件
        recoverPasswordButton.setOnClickListener(v -> {
            // 获取用户输入的账号并去除首尾空格
            String recoverUsername = Objects.requireNonNull(recoverPasswordInput.getText()).toString().trim();
            // 检查账号是否为空
            if (recoverUsername.isEmpty()) {
                // 显示自定义Toast提示用户输入账号
                showCustomToast("请输入账号");
                return;
            }

            // 验证账号是否为默认账号
            if ("admin".equals(recoverUsername)) {
                // 显示找回的密码
                passwordResult.setText("您的密码是：123");
                // 显示密码结果文本视图
                passwordResult.setVisibility(View.VISIBLE);
                showPasswordNotification();
            } else {
                // 显示自定义Toast提示未找到该账号
                showCustomToast("未找到该账号");
                // 隐藏密码结果文本视图
                passwordResult.setVisibility(View.GONE);
            }
        });

        // 设置点击菜单外区域关闭菜单
        slideMenu.setOnTouchListener((v, event) -> {
            // 只处理点击事件
            if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                // 获取点击位置（屏幕坐标）
                float x = event.getRawX();
                float y = event.getRawY();
                
                // 获取菜单容器
                View menuContainer = slideMenu.findViewById(R.id.menu_container);
                
                // 获取菜单容器的位置和尺寸
                int[] location = new int[2];
                menuContainer.getLocationOnScreen(location);
                int menuLeft = location[0];
                int menuTop = location[1];
                int menuRight = menuLeft + menuContainer.getWidth();
                int menuBottom = menuTop + menuContainer.getHeight();
                
                // 判断点击位置是否在菜单容器外
                if (x < menuLeft || x > menuRight || y < menuTop || y > menuBottom) {
                    hideMenu();
                    return true;
                }
            }
            return true;
        });

        
        // 防止点击菜单内容区域关闭菜单
        // 拦截点击事件，防止传递到父视图
        slideMenu.findViewById(R.id.menu_container).setOnClickListener(v -> {});

        // 设置图标点击动画
        // 从布局中获取图标ImageView
        ImageView iconImage = findViewById(R.id.iconImage);
        iconImage.setOnClickListener(v -> {
            // 加载图标点击动画
            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.icon_click_animation);
            // 为图标应用动画
            v.startAnimation(animation);
        });

        // 设置登录按钮点击事件
        loginButton.setOnClickListener(v -> {
            // 获取输入内容
            // 获取用户输入的昵称并去除首尾空格
            String nickname = Objects.requireNonNull(nicknameInput.getText()).toString().trim();
            // 获取用户输入的密码并去除首尾空格
            String password = Objects.requireNonNull(passwordInput.getText()).toString().trim();
            // 获取用户是否同意协议的状态
            boolean isAgreed = agreementCheckbox.isChecked();

            // 简单的输入验证
            if (nickname.isEmpty() || password.isEmpty()) {
                // 显示自定义Toast提示账号或密码不能为空
                showCustomToast("账号或密码不能为空");
                return;
            }

            if (!isAgreed) {
                // 显示自定义Toast提示用户同意协议
                showCustomToast("请同意用户协议");
                return;
            }

            // 验证默认账号
            if ("admin".equals(nickname)) {
                if ("123".equals(password)) {
                    // 显示自定义Toast提示登录成功
                    showCustomToast("登录成功");
                    // 创建跳转到主页的意图
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    // 启动主页活动
                    startActivity(intent);
                    // 添加页面切换动画
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    // 显示自定义Toast提示账号或密码输入错误
                    showCustomToast("账号或密码输入错误");
                }
                return;
            }

            // 其他账号的登录逻辑
            // 显示自定义Toast提示登录成功
            showCustomToast("登录成功");
        });

        // 设置注册链接点击事件
        // 为跳转注册文本添加下划线
        // 创建可设置样式的字符串
        SpannableString registerSpannable = new SpannableString(getString(R.string.register_link));
        // 为字符串添加下划线样式
        registerSpannable.setSpan(new UnderlineSpan(), 0, registerSpannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置注册链接文本视图的文本
        registerLink.setText(registerSpannable);

        registerLink.setOnClickListener(v -> {
            // 创建跳转到注册页面的意图
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            // 启动注册活动
            startActivity(intent);
            // 添加页面切换动画
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    /**
     * 创建选项菜单时调用的方法。
     * @param menu 要创建的菜单对象
     * @return 如果菜单创建成功返回true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 加载菜单布局
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // 获取菜单图标项
        MenuItem menuItem = menu.findItem(R.id.menu_icon);
        // 确保菜单图标可见
        if (menuItem != null) {
            menuItem.setVisible(true);
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return true;
    }

    /**
     * 选项菜单项被选中时调用的方法。
     * @param item 被选中的菜单项
     * @return 如果菜单项处理成功返回true，否则调用父类的处理方法
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_icon) {
            // 切换菜单的显示状态
            toggleMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 切换菜单的显示状态。
     */
    private void toggleMenu() {
        // 添加日志输出，确认方法被调用
        showCustomToast("切换菜单状态");
        
        if (isMenuVisible) {
            // 隐藏菜单
            hideMenu();
        } else {
            // 显示菜单
            showMenu();
        }
    }

    /**
     * 显示菜单并应用下滑动画。
     */
    private void showMenu() {
        // 显示滑动菜单
        slideMenu.setVisibility(View.VISIBLE);
        // 加载下滑动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        // 为菜单容器应用下滑动画
        slideMenu.findViewById(R.id.menu_container).startAnimation(animation);
        // 标记菜单为可见状态
        isMenuVisible = true;
        // 添加日志输出，确认方法被调用
        showCustomToast("菜单已显示");
    }

    /**
     * 隐藏菜单并应用上滑动画。
     */
    private void hideMenu() {
        // 加载上滑动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        // 设置动画监听器
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // 动画结束后隐藏滑动菜单
                slideMenu.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        // 为菜单容器应用上滑动画
        slideMenu.findViewById(R.id.menu_container).startAnimation(animation);
        // 标记菜单为不可见状态
        isMenuVisible = false;
    }
}