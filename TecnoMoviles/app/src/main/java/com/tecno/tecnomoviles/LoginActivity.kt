package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {

    private lateinit var registerButton: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        supportActionBar?.hide()

        registerButton = findViewById(R.id.registerButton)

        val registerIntent = Intent(this, RegisterActivity::class.java)


        registerButton.setOnClickListener(){
            startActivity(registerIntent)
        }

    }
}