package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tecno.tecnomoviles.databinding.ConfirmacionCompraBinding
import com.tecno.tecnomoviles.databinding.EditDataUserBinding
import com.tecno.tecnomoviles.databinding.LoginBinding

class ConfirmacionCompra  : AppCompatActivity() {

    private lateinit var binding: ConfirmacionCompraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirmacion_compra)
        supportActionBar?.hide()

        binding = ConfirmacionCompraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.aceptar.setOnClickListener(){
            startActivity( Intent(this, HomeActivity::class.java))
        }

        binding.cancelar.setOnClickListener(){
            startActivity( Intent(this, HomeActivity::class.java))
        }

    }
}