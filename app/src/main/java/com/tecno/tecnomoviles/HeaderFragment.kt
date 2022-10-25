package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HeaderFragment: AppCompatActivity() {

    private lateinit var carrito: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.header_fragment)
        supportActionBar?.hide()

        carrito = findViewById(R.id.chop)
        carrito.setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
            Toast.makeText(baseContext, "ESTO FUNCA", Toast.LENGTH_SHORT).show()
        }

    }


}