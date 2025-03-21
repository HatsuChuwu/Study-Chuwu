package com.example.registerhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var rememberPasswordCheckBox: CheckBox
    private lateinit var loginButton: Button
    private lateinit var registerLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 初始化视图
        initViews()
        // 设置点击事件
        setupClickListeners()
    }

    private fun initViews() {
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        rememberPasswordCheckBox = findViewById(R.id.rememberPasswordCheckBox)
        loginButton = findViewById(R.id.loginButton)
        registerLink = findViewById(R.id.registerLink)
    }

    private fun setupClickListeners() {
        // 登录按钮点击事件
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // 验证输入
            if (validateInput(username, password)) {
                // 执行登录
                performLogin(username, password)
            }
        }

        // 注册链接点击事件
        registerLink.setOnClickListener {
            // 跳转到注册页面
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        // 验证用户名
        if (username.isEmpty()) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show()
            return false
        }

        // 验证密码
        if (password.isEmpty()) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun performLogin(username: String, password: String) {
        // 这里应该实现实际的登录逻辑，例如调用API或验证本地存储的凭据
        // 为了演示，我们只显示一个成功消息
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()

        // 如果选中了"记住密码"，可以在这里保存用户凭据
        if (rememberPasswordCheckBox.isChecked) {
            // 这里可以实现保存用户凭据的逻辑
            // 例如使用SharedPreferences
        }

        // 登录成功后跳转到主页面
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    } // 结束当前活动，防止用户按返回键返回登录页
}