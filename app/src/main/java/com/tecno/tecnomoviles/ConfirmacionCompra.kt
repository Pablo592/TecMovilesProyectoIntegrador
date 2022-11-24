package com.tecno.tecnomoviles

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.MyApplication
import com.tecno.tecnomoviles.databinding.ConfirmacionCompraBinding
import com.tecno.tecnomoviles.databinding.EditDataUserBinding
import com.tecno.tecnomoviles.databinding.LoginBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.user.User

class ConfirmacionCompra  : AppCompatActivity() {

    private lateinit var binding: ConfirmacionCompraBinding
    val userLiveData = MutableLiveData<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirmacion_compra)
        supportActionBar?.hide()

        binding = ConfirmacionCompraBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.cancelar.setOnClickListener(){
            startActivity( Intent(this, HomeActivity::class.java))
        }

        binding.aceptar.setOnClickListener(){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BIND_CARRIER_MESSAGING_CLIENT_SERVICE) !=
                PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BIND_CARRIER_MESSAGING_CLIENT_SERVICE),
                    PackageManager.GET_PERMISSIONS)
            }


            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("pgaido524@alumnos.iua.edu.ar"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Este es un mail de prueba")
            intent.putExtra(Intent.EXTRA_TEXT, "Mensaje texto")
            val mailer = Intent.createChooser(intent, "Enviar mail usando")
            startActivity(mailer)


            startActivity( Intent(this, HomeActivity::class.java))


        }

    }

    override fun onResume() {
        super.onResume()

    }

    private fun getProfileForDatabase() {
        runBlocking {
            launch {
                if (MyApplication.preferences.getUserName().isNotEmpty() || MyApplication.preferences.getUserName().isNotBlank()) {
                    if (MyApplication.myAppDatabase.userDao().isEmpty(MyApplication.preferences.getUserName()) == 0
                    ) {
                        Toast.makeText(baseContext, "Ingrese datos correctos o Registrese", Toast.LENGTH_LONG).show()
                    } else {
                        if (MyApplication.preferences.getUserName().isNotEmpty() || MyApplication.preferences.getUserName().isNotBlank()) {
                            userLiveData.value = MyApplication.myAppDatabase.userDao()
                                .getUser(MyApplication.preferences.getUserName())
                        }
                    }
                }
            }
        }
    }
}