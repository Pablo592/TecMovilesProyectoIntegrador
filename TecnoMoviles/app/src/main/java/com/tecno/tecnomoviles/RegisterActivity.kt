package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity: AppCompatActivity() {

    private lateinit var returnButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_user)
        supportActionBar?.hide()

        returnButton = findViewById(R.id.returnButton)

        val loginIntent = Intent(this, LoginActivity::class.java)


        returnButton.setOnClickListener(){
            startActivity(loginIntent)
        }
    }
}