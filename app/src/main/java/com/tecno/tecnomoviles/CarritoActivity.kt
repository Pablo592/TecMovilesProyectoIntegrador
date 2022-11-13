package com.tecno.tecnomoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.MyApplication
import utils.fragments.HeaderFragment

class CarritoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HeaderFragment>(R.id.fragment_header)
            }
        }

        val recyclerViewProductoCarrito = findViewById<RecyclerView>(R.id.recyclerViewCarrito)
        val adapterProductoCarrito = CustomAdapterProductoCarrito()

        recyclerViewProductoCarrito.layoutManager = LinearLayoutManager(this)
        recyclerViewProductoCarrito.adapter = adapterProductoCarrito

        MyApplication.preferences.setActivityName("CarritoActivity")
    }
}