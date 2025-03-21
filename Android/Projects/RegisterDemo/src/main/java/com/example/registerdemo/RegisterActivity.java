package com.example.registerdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText nicknameInput;
    private TextInputEditText passwordInput;
    private CheckBox agreementCheckbox;
    private View slideMenu;
    private RadioGroup genderGroup;
    private CheckBox readingCheckbox;
    private CheckBox musicCheckbox;
    private CheckBox gameCheckbox;
    private CheckBox runningCheckbox;
    private AutoCompleteTextView deptSpinner;

    private void showCustomToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));
        ImageView image = layout.findViewById(R.id.toast_image);
        image.setImageResource(R.drawable.bocchi);
        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 设置工具栏
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // 添加退出图标
        toolbar.setNavigationIcon(R.drawable.exit_icon);
        toolbar.setNavigationOnClickListener(v -> showExitDialog());

        nicknameInput = findViewById(R.id.nicknameInput);
        passwordInput = findViewById(R.id.passwordInput);
        agreementCheckbox = findViewById(R.id.agreementCheckbox);
        Button registerButton = findViewById(R.id.registerButton);
        TextView loginLink = findViewById(R.id.loginLink);
        Button receiveDataButton = findViewById(R.id.btnReceiveData);

        // 为账号和密码输入框添加长按事件监听器
        nicknameInput.setOnLongClickListener(v -> {
            showClearDialog();
            return true;
        });

        passwordInput.setOnLongClickListener(v -> {
            showClearDialog();
            return true;
        });

        receiveDataButton.setOnClickListener(v -> {
            nicknameInput.setText("admin");
            passwordInput.setText("123");
            showCustomToast("已接收预设账号信息");
        });

        // 初始化专业方向下拉菜单（Material Design风格）
        deptSpinner = findViewById(R.id.spDept);
        // 创建专业方向数据
        String[] departments = {"请选择专业方向", "物联网技术系", "软件技术系", "网络技术系"};
        // 创建适配器（使用适合下拉菜单的布局）
        ArrayAdapter<String> deptAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, departments);
        // 将适配器设置到AutoCompleteTextView
        deptSpinner.setAdapter(deptAdapter);
        // 设置默认选项
        deptSpinner.setText(departments[0], false);

        // 初始化性别选择组件
        genderGroup = findViewById(R.id.genderGroup);

        // 初始化爱好选择组件
        readingCheckbox = findViewById(R.id.readingCheckbox);
        musicCheckbox = findViewById(R.id.musicCheckbox);
        gameCheckbox = findViewById(R.id.gameCheckbox);
        runningCheckbox = findViewById(R.id.runningCheckbox);

        slideMenu = getLayoutInflater().inflate(R.layout.slide_menu, null);
        ((ViewGroup) getWindow().getDecorView()).addView(slideMenu);
        slideMenu.setVisibility(View.GONE);

        slideMenu.setOnClickListener(v -> hideMenu());
        slideMenu.findViewById(R.id.menu_container).setOnClickListener(View::performClick);

        registerButton.setOnClickListener(v -> {
            String nickname = Objects.requireNonNull(nicknameInput.getText()).toString().trim();
            String password = Objects.requireNonNull(passwordInput.getText()).toString().trim();
            boolean isAgreed = agreementCheckbox.isChecked();

            // 验证输入
            if (nickname.isEmpty() || password.isEmpty()) {
                showCustomToast("账号和密码不能为空");
                return;
            }

            // 验证专业方向选择
            String selectedDept = deptSpinner.getText().toString().trim();
            if (selectedDept.isEmpty() || selectedDept.equals("请选择专业方向")) {
                showCustomToast("请选择专业方向");
                return;
            }

            // 验证性别选择
            if (genderGroup.getCheckedRadioButtonId() == -1) {
                showCustomToast("请选择性别");
                return;
            }

            // 验证爱好选择
            if (!readingCheckbox.isChecked() && !musicCheckbox.isChecked() && 
                !gameCheckbox.isChecked() && !runningCheckbox.isChecked()) {
                showCustomToast("请至少选择一个爱好");
                return;
            }

            if (!isAgreed) {
                showCustomToast("请同意用户协议");
                return;
            }

            // 获取专业方向信息
            String department = deptSpinner.getText().toString().trim();

            // 获取性别信息
            String gender = "";
            int checkedRadioButtonId = genderGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId != -1) {
                RadioButton selectedRadioButton = findViewById(checkedRadioButtonId);
                gender = selectedRadioButton.getText().toString();
            }

            // 获取爱好信息
            StringBuilder hobbies = new StringBuilder();
            if (readingCheckbox.isChecked()) {
                hobbies.append("读书 ");
            }
            if (musicCheckbox.isChecked()) {
                hobbies.append("听音乐 ");
            }
            if (gameCheckbox.isChecked()) {
                hobbies.append("打游戏 ");
            }
            if (runningCheckbox.isChecked()) {
                hobbies.append("跑步 ");
            }

            // 显示包含用户选择信息的成功提示
            showCustomToast("注册成功！\n用户名：" + nickname + "\n专业：" + department + "\n性别：" + gender + "\n爱好：" + hobbies.toString().trim());
            
            // 跳转到登录页面
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            // 可以传递用户名到登录页面，方便用户登录
            intent.putExtra("username", nickname);
            startActivity(intent);
            // 添加页面切换动画，与MainActivity中的登录跳转动画保持一致
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        });

        SpannableString loginSpannable = new SpannableString(getString(R.string.login_link));
        loginSpannable.setSpan(new UnderlineSpan(), 0, loginSpannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        loginLink.setText(loginSpannable);

        loginLink.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    // 显示清除内容对话框
    private void showClearDialog() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("清除内容")
            .setMessage("确定要清除账号和密码吗？")
            .setPositiveButton("确定", (dialog, which) -> {
                nicknameInput.setText("");
                passwordInput.setText("");
                showCustomToast("已清除输入内容");
            })
            .setNegativeButton("取消", null)
            .show();
    }

    private void hideMenu() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                slideMenu.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        slideMenu.findViewById(R.id.menu_container).startAnimation(animation);
    }

    private void showExitDialog() {
        new com.google.android.material.dialog.MaterialAlertDialogBuilder(this)
            .setIcon(R.drawable.exit_icon)
            .setTitle("退出系统")
            .setMessage("确定要退出系统吗？")
            .setPositiveButton("退出", (dialog, which) -> {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            })
            .setNegativeButton("取消", null)
            .show();
    }
}