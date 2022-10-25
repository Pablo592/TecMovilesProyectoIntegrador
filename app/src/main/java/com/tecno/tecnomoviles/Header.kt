package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tecno.tecnomoviles.databinding.HeaderFragmentBinding

class Header: AppCompatActivity() {

    private lateinit var binding: HeaderFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.header_fragment)
        supportActionBar?.hide()

        binding = HeaderFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chop.setOnClickListener() {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }


        /*
        val carrito = findViewById<ImageView>(R.id.chop)
        carrito.setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))

        }
        */

    }


}