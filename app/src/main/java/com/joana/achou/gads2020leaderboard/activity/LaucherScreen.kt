package com.joana.achou.gads2020leaderboard.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.joana.achou.gads2020leaderboard.R

class LaucherScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laucher_screen)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        },3000
        )
    }
}