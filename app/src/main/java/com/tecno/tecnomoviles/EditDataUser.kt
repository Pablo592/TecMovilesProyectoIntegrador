package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tecno.tecnomoviles.databinding.EditDataUserBinding

class EditDataUser : AppCompatActivity() {

    private lateinit var binding: EditDataUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_data_user)
        supportActionBar?.hide()

        binding = EditDataUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changeName.setOnClickListener(){

            if(binding.nameInput.visibility == View.VISIBLE){
                binding.nameInput.visibility = View.INVISIBLE
            }else{
                binding.nameInput.visibility = View.VISIBLE
            }
        }

        binding.changeUsername.setOnClickListener(){

            if(binding.usernameInput.visibility == View.VISIBLE){
                binding.usernameInput.visibility = View.INVISIBLE
            }else{
                binding.usernameInput.visibility = View.VISIBLE
            }
        }

        binding.changeEmail.setOnClickListener(){

            if(binding.emailInput.visibility == View.VISIBLE){
                binding.emailInput.visibility = View.INVISIBLE
            }else{
                binding.emailInput.visibility = View.VISIBLE
            }
        }

        binding.changePassword.setOnClickListener(){
            if(binding.contraseniaNuevoInput.visibility == View.VISIBLE){
                binding.contraseniaNuevoInput.visibility = View.INVISIBLE
                binding.contraseniaActualInput.visibility = View.INVISIBLE
            }else{
                binding.contraseniaNuevoInput.visibility = View.VISIBLE
                binding.contraseniaActualInput.visibility = View.VISIBLE
            }
        }
        binding.guardarCambios.setOnClickListener(){

            when{
                binding.nameInput.text.isEmpty() && binding.nameInput.visibility == View.VISIBLE -> {
                    Toast.makeText(baseContext,"Ingrese su nuevo nombre", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                binding.usernameInput.text.isEmpty() && binding.usernameInput.visibility == View.VISIBLE -> {
                    Toast.makeText(baseContext,"Ingrese su nuevo usuario", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                binding.emailInput.text.isEmpty() && binding.emailInput.visibility == View.VISIBLE -> {
                    Toast.makeText(baseContext,"Ingrese su email", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                !binding.emailInput.text.contains("@") && binding.emailInput.visibility == View.VISIBLE -> {
                    Toast.makeText(baseContext,"Ingrese un email válido", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}


                binding.contraseniaActualInput.text.isEmpty() && binding.contraseniaActualInput.visibility == View.VISIBLE -> {
                    Toast.makeText(baseContext,"Ingrese su contraseña actual", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                binding.contraseniaNuevoInput.text.isEmpty() && binding.contraseniaNuevoInput.visibility == View.VISIBLE -> {
                    Toast.makeText(baseContext,"Ingrese su nueva contraseña", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

            }
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.cancelarCambios.setOnClickListener(){
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}