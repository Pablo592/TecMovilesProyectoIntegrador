package com.tecno.tecnomoviles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RegistroDeUsuarioActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_usuario)
        supportActionBar?.hide()

    }
}