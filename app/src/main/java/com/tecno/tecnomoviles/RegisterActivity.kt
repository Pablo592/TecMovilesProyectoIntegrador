package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.tecno.tecnomoviles.databinding.LoginBinding
import com.tecno.tecnomoviles.databinding.RegisterUserBinding

class RegisterActivity: AppCompatActivity() {

    private lateinit var binding : RegisterUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_user)
        supportActionBar?.hide()

        binding = RegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.returnButton.setOnClickListener{

            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}