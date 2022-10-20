package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.view.View
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
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.cancelarCambios.setOnClickListener(){
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}