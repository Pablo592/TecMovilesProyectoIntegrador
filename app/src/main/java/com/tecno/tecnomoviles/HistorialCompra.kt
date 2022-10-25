package com.tecno.tecnomoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tecno.tecnomoviles.databinding.EditDataUserBinding
import com.tecno.tecnomoviles.databinding.HistorialCompraBinding
import utils.fragments.HeaderFragment

class HistorialCompra : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_compra)
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HeaderFragment>(R.id.fragment_header)
            }
        }

        val recyclerViewHistorialCompra = findViewById<RecyclerView>(R.id.recyclerViewHistory)
        val adapterHistorialCompra = CustomAdapterHistorialCompra()

        recyclerViewHistorialCompra.layoutManager = LinearLayoutManager(this)
        recyclerViewHistorialCompra.adapter = adapterHistorialCompra

    }




}