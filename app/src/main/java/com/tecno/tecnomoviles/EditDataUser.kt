package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import com.MyApplication
import com.tecno.tecnomoviles.databinding.EditDataUserBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.user.User

class EditDataUser : AppCompatActivity() {

    private lateinit var binding: EditDataUserBinding
    val userLiveData = MutableLiveData<User>()
    var existente: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_data_user)
        supportActionBar?.hide()

        binding = EditDataUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changeName.setOnClickListener(){

            if(binding.nameInput.isVisible){
                binding.nameInput.visibility = View.INVISIBLE
            }else{
                binding.nameInput.visibility = View.VISIBLE
            }
        }

        binding.changeUsername.setOnClickListener(){

            if(binding.usernameInput.isVisible){
                binding.usernameInput.visibility = View.INVISIBLE
            }else{
                binding.usernameInput.visibility = View.VISIBLE
            }
        }

        binding.changeEmail.setOnClickListener(){

            if(binding.emailInput.isVisible){
                binding.emailInput.visibility = View.INVISIBLE
            }else{
                binding.emailInput.visibility = View.VISIBLE
            }
        }

        binding.changePassword.setOnClickListener(){
            if(binding.contraseniaNuevoInput.isVisible){
                binding.contraseniaNuevoInput.visibility = View.INVISIBLE
                binding.contraseniaActualInput.visibility = View.INVISIBLE
            }else{
                binding.contraseniaNuevoInput.visibility = View.VISIBLE
                binding.contraseniaActualInput.visibility = View.VISIBLE
            }
        }
        binding.guardarCambios.setOnClickListener(){

            when{
                binding.nameInput.text.isEmpty() && binding.nameInput.isVisible -> {
                    Toast.makeText(baseContext,"Ingrese su nuevo nombre", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                binding.usernameInput.text.isEmpty() && binding.usernameInput.isVisible -> {
                    Toast.makeText(baseContext,"Ingrese su nuevo usuario", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                binding.emailInput.text.isEmpty() && binding.emailInput.isVisible -> {
                    Toast.makeText(baseContext,"Ingrese su email", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                !binding.emailInput.text.contains("@") && binding.emailInput.isVisible -> {
                    Toast.makeText(baseContext,"Ingrese un email válido", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}


                binding.contraseniaActualInput.text.isEmpty() && binding.contraseniaActualInput.isVisible -> {
                    Toast.makeText(baseContext,"Ingrese su contraseña actual", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}

                binding.contraseniaNuevoInput.text.isEmpty() && binding.contraseniaNuevoInput.isVisible -> {
                    Toast.makeText(baseContext,"Ingrese su nueva contraseña", Toast.LENGTH_LONG).show()
                    return@setOnClickListener}
            }
            getProfileForDatabase()


            userLiveData.observe(this, Observer{
                if((binding.contraseniaActualInput.isVisible) && (binding.contraseniaActualInput.text.toString() != it.password)){
                    Toast.makeText(baseContext,"Ingrese correctamente su contraseña actual", Toast.LENGTH_LONG).show()
                    return@Observer
                }

                if(binding.usernameInput.isVisible)
                it.id?.let { it1 -> existUsername(it1) }

                if(existente){
                    updateUser(
                        it.id,
                        isVisible(binding.usernameInput, it.username),
                        isVisible(binding.contraseniaNuevoInput, it.password),
                        isVisible(binding.nameInput, it.name),
                        isVisible(binding.emailInput, it.email),
                        it.profilePhotoUrl)

                    MyApplication.preferences.setUserName(isVisible(binding.usernameInput, it.username))
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
            })
        }

        binding.cancelarCambios.setOnClickListener(){
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun getProfileForDatabase() {
        runBlocking {
            launch {
                if (MyApplication.preferences.getUserName().isNotEmpty() || MyApplication.preferences.getUserName().isNotBlank()) {
                    userLiveData.value = MyApplication.myAppDatabase.userDao()
                        .getUser(MyApplication.preferences.getUserName())
                }
            }
        }
    }


    private fun existUsername(id:Int) {
        runBlocking {
            launch {
                if (MyApplication.myAppDatabase.userDao().isEmpty(binding.usernameInput.text.toString()) > 0) {
                    if (MyApplication.myAppDatabase.userDao().existUsernameForEdit(binding.usernameInput.text.toString()) != id) {
                        Toast.makeText(
                            baseContext,
                            "El usuario " + binding.usernameInput.text.toString() + " ya existe",
                            Toast.LENGTH_LONG
                        ).show()
                        existente = false
                    } else {
                        existente = true
                    }
                }else{
                    existente = true
                }
            }
        }
    }

    private fun updateUser(id:Int?,username:String,password:String,name:String,email:String,profilePhoto:String){
        runBlocking {
            MyApplication.myAppDatabase.userDao().updateUser(
                User(
                    id = id,
                    username = username,
                    password = password,
                    name = name,
                    email = email,
                    profilePhotoUrl = profilePhoto
                )
            )
        }
    }

    private fun isVisible(inputData: EditText, bdData:String): String {
        if(inputData.isVisible){
            return inputData.text.toString()
        }else{
            return bdData
        }
    }
}