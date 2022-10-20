package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tecno.tecnomoviles.databinding.ProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)
        supportActionBar?.hide()

        binding = ProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editDataButton.setOnClickListener(){
            startActivity(Intent(this, EditDataUser::class.java))
        }
    }
}