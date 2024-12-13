package com.song.androidapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 隐藏 ActionBar（标题栏）
        supportActionBar?.hide()

        // 隐藏状态栏和虚拟导航栏（底部按钮）
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN   // 隐藏状态栏
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  // 隐藏底部虚拟导航栏
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY  // 保持隐藏状态
                )

        val usernameEditText: EditText = findViewById(R.id.username)
        val passwordEditText: EditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show()
            } else {
                if (username == "admin" && password == "12345") {
                    val json = "name=宋俊伟&password=1"
                    postRequest("http://154.8.141.131:9090/imgs/getImgs", json)
                    // 登录成功，跳转到主页
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                    finish()  // 结束当前登录页面
                } else {
                    Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
