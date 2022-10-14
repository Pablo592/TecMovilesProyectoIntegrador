package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity() {

    private lateinit var registerButton: TextView
    private lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        supportActionBar?.hide()

        registerButton = findViewById(R.id.registerButton)
        loginButton = findViewById(R.id.loginButton)

        val registerIntent = Intent(this, RegisterActivity::class.java)
        val loginIntent = Intent(this, HomeActivity::class.java)

        registerButton.setOnClickListener(){
            startActivity(registerIntent)
        }

        loginButton.setOnClickListener(){
            startActivity(loginIntent)
        }

    }
}