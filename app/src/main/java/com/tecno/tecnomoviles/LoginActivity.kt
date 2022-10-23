package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tecno.tecnomoviles.databinding.LoginBinding

class LoginActivity: AppCompatActivity() {

    private lateinit var binding : LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        supportActionBar?.hide()

        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener{

        startActivity( Intent(this, RegisterActivity::class.java))
        }

        binding.loginButton.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }

    }
}