package com.tecno.tecnomoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class DetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val producto = intent.getParcelableExtra<Producto>("producto")
        if (producto!=null){
            val imageView: ImageView = findViewById(R.id.detailedActivityImage)

            imageView.setImageResource(producto.images)
        }


    }
}