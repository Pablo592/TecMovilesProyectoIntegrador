package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

        binding.loginButton.setOnClickListener(){
            when{
                binding.userInput.text.isEmpty() -> {
                    Toast.makeText(baseContext,"Ingrese su usuario",Toast.LENGTH_LONG).show()
                return@setOnClickListener}

                binding.inputPassword.text.isEmpty() -> {
                    Toast.makeText(baseContext,"Ingrese su contraseña",Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                binding.inputPassword.text.toString() != "123" -> {
                    Toast.makeText(baseContext,"Usuario o contraseña incorrecto",Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

            }
            startActivity(Intent(this, HomeActivity::class.java))
        }

    }
}