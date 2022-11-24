package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.MyApplication
import com.tecno.tecnomoviles.databinding.RegisterUserBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.user.User

class RegisterActivity: AppCompatActivity() {

    private lateinit var binding : RegisterUserBinding
    var existente: Boolean = false

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

                binding.nameInput.text.isEmpty() -> {
                    Toast.makeText(baseContext,"Ingrese su nombre", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                binding.inputPassword.text.isEmpty() -> {
                    Toast.makeText(baseContext,"Ingrese su contraseña", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}
            }
            getProfileForDatabase()

            if(existente){
                saveUser()
                startActivity(Intent(this, LoginActivity::class.java))
            }

        }

        binding.textoCancelar.setOnClickListener(){
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun getProfileForDatabase() {
        runBlocking {
            launch {
                if (MyApplication.preferences.getUserName()
                        .isNotEmpty() || MyApplication.preferences.getUserName().isNotBlank()) {
                    if (MyApplication.myAppDatabase.userDao()
                            .isEmpty(binding.userInput.text.toString()) > 0
                    ) {
                        Toast.makeText(
                            baseContext,
                            "El usuario " + binding.userInput.text.toString() + " ya existe",
                            Toast.LENGTH_LONG
                        ).show()
                        existente = false
                    }else{
                        existente = true
                    }
                }
            }
        }
    }



    private fun saveUser(){
        runBlocking {
        MyApplication.myAppDatabase.userDao().setUser(
            User(
                username = binding.userInput.text.toString(),
                password = binding.inputPassword.text.toString(),
                name = binding.nameInput.text.toString(),
                email = binding.inputEmail.text.toString(),
                profilePhotoUrl = "NADA"
            )
        )
        }
    }
}