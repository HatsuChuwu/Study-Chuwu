package com.example.registerhomework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var agreementCheckBox: CheckBox
    private lateinit var registerButton: Button
    private lateinit var loginLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // 初始化视图
        initViews()
        // 设置点击事件
        setupClickListeners()
    }

    private fun initViews() {
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        agreementCheckBox = findViewById(R.id.agreementCheckBox)
        registerButton = findViewById(R.id.registerButton)
        loginLink = findViewById(R.id.loginLink)
    }

    private fun setupClickListeners() {
        // 注册按钮点击事件
        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            // 验证输入
            if (validateInput(username, password, confirmPassword)) {
                // 执行注册
                performRegister(username, password)
            }
        }

        // 登录链接点击事件
        loginLink.setOnClickListener {
            // 跳转到登录页面
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // 结束当前活动，防止用户按返回键返回注册页面
        }
    }

    private fun validateInput(username: String, password: String, confirmPassword: String): Boolean {
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

        // 验证确认密码
        if (confirmPassword.isEmpty()) {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show()
            return false
        }

        // 验证两次密码是否一致
        if (password != confirmPassword) {
            Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show()
            return false
        }

        // 验证是否同意协议
        if (!agreementCheckBox.isChecked) {
            Toast.makeText(this, "请同意协议", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun performRegister(username: String, password: String) {
        // 这里应该实现实际的注册逻辑，例如调用API或将用户信息存储到本地
        // 为了演示，我们只显示一个成功消息
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()

        // 注册成功后跳转到登录页面
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // 结束当前活动
    }
}