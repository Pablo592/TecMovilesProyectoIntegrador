package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tecno.tecnomoviles.databinding.LoginBinding
import com.tecno.tecnomoviles.databinding.NavigationDrawerBinding

class NavigationDrawer : AppCompatActivity() {

    private lateinit var binding : NavigationDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_drawer)
        supportActionBar?.hide()

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
    }
}

