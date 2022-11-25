package com.tecno.tecnomoviles

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.MyApplication
import com.tecno.tecnomoviles.databinding.LoginBinding
import com.tecno.tecnomoviles.databinding.NavigationDrawerBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.user.User

class NavigationDrawer : AppCompatActivity() {

    private lateinit var binding : NavigationDrawerBinding
    val userLiveData = MutableLiveData<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_drawer)
        supportActionBar?.hide()
        getProfileForDatabase()

        binding = NavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profile.setOnClickListener(){
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.volver.setOnClickListener(){
            onBackPressed()
        }

        binding.imageHome.setOnClickListener(){
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.shoppingHistory.setOnClickListener() {
            startActivity(Intent(this, HistorialCompra::class.java))
        }

        binding.signOff.setOnClickListener(){
            MyApplication.preferences.setUserName("")
            MyApplication.preferences.setUserPassword("")
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        userLiveData.observe(this, Observer{
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PackageManager.GET_PERMISSIONS)
            }
            if(it.profilePhotoUrl != "NADA")
                binding.imageProfile.setImageURI(Uri.parse(it.profilePhotoUrl))
        })
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
}


