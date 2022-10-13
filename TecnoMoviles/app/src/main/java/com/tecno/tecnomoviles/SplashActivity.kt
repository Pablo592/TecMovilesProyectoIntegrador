package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        supportActionBar?.hide()

        val mainIntent = Intent(this, LoginActivity::class.java)

        Timer().schedule(timerTask {
            startActivity(mainIntent)
        }, 1500)


    }
}

