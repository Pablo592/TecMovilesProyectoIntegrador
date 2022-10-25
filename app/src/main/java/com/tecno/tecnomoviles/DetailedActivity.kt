package com.tecno.tecnomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.tecno.tecnomoviles.databinding.ConfirmacionCompraBinding
import com.tecno.tecnomoviles.databinding.LoginBinding

class DetailedActivity : AppCompatActivity() {

    private lateinit var botonComprar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        botonComprar = findViewById(R.id.guardarCambios)
        val producto = intent.getParcelableExtra<Producto>("producto")
        if (producto!=null){
            val imageView: ImageView = findViewById(R.id.detailedActivityImage)

            imageView.setImageResource(producto.images)
        }

<<<<<<< HEAD

=======
        botonComprar.setOnClickListener(){
           startActivity(Intent(this, ConfirmacionCompra::class.java))
       }
>>>>>>> 1b7e0e809aa94551929d8073565de3cddbcce3a6
    }
}