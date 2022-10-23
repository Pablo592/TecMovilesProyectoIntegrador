package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NavigationDrawer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_drawer)
        supportActionBar?.hide()
        startActivity(Intent(this, NavigationDrawer::class.java))
    }
}

