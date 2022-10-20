package com.tecno.tecnomoviles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class EditDataUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_data_user)
        supportActionBar?.hide()
    }
}