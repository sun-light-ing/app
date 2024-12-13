package com.song.androidapp

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // 设置状态栏颜色与主体背景一致
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.colorPrimary)  // 与主体背景一致的颜色
        }
        supportActionBar?.hide()
    }
}
