package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
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

        binding.textoCrearCuenta.setOnClickListener(){
            when{
                binding.inputEmail.text.isEmpty() -> {
                    Toast.makeText(baseContext,"Ingrese su email", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                !binding.inputEmail.text.contains("@") -> {
                    Toast.makeText(baseContext,"Ingrese un email válido", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                binding.userInput.text.isEmpty() -> {
                    Toast.makeText(baseContext,"Ingrese su usuario", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                binding.inputPassword.text.isEmpty() -> {
                    Toast.makeText(baseContext,"Ingrese su contraseña", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}
            }
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}