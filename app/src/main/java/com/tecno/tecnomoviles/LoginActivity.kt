package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.MyApplication
import com.tecno.tecnomoviles.databinding.LoginBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.user.User

class LoginActivity: AppCompatActivity() {

    private lateinit var binding : LoginBinding
    val userLiveData = MutableLiveData<User>()

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
            }
            getProfileForDatabase()
            userLiveData.observe(this, Observer{

                if(it.username == null){
                    Toast.makeText(
                        baseContext,
                        "Ingrese datos correctos o Registrese",
                        Toast.LENGTH_LONG
                    ).show()
                }else{
                    if(binding.inputPassword.text.toString() == it.password) {
                        startActivity(Intent(this, HomeActivity::class.java))
                    }else{
                        Toast.makeText(baseContext,"Ingrese datos correctos o Registrese", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }



    private fun getProfileForDatabase() {
        runBlocking {
            launch {
                if (MyApplication.preferences.getUserName().isNotEmpty() || MyApplication.preferences.getUserName().isNotBlank()) {
                    if (MyApplication.myAppDatabase.userDao().isEmpty(binding.userInput.text.toString()) == 0
                    ) {
                        Toast.makeText(baseContext, "Ingrese datos correctos o Registrese", Toast.LENGTH_LONG).show()
                    } else {
                        if (MyApplication.preferences.getUserName().isNotEmpty() || MyApplication.preferences.getUserName().isNotBlank()) {
                            userLiveData.value = MyApplication.myAppDatabase.userDao()
                                .getUser(binding.userInput.text.toString())
                        }
                    }
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        if (MyApplication.preferences.getUserName().isNotEmpty() || MyApplication.preferences.getUserName().isNotBlank()) {
            binding.userInput.setText(MyApplication.preferences.getUserName())
            binding.inputPassword.setText("")
        }

        if ((MyApplication.preferences.getUserPassword().isNotEmpty() || MyApplication.preferences.getUserPassword().isNotBlank())
            && (MyApplication.preferences.getUserName().isNotEmpty() || MyApplication.preferences.getUserName().isNotBlank())) {
            binding.userInput.setText(MyApplication.preferences.getUserName())
            binding.inputPassword.setText(MyApplication.preferences.getUserPassword())
            getProfileForDatabase()

            userLiveData.observe(this, Observer{

                if(it.username != null){
                    if(binding.inputPassword.text.toString() == it.password) {
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                }
            })
        }
    }

    override fun onPause() {
        super.onPause()
        MyApplication.preferences.setUserName(binding.userInput.text.toString())
        MyApplication.preferences.setUserPassword(binding.inputPassword.text.toString())
        binding.userInput.setText("")
    }
}