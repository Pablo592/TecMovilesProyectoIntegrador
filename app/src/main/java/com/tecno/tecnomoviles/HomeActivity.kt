package com.tecno.tecnomoviles

import android.os.Bundle
import android.widget.HorizontalScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import utils.fragments.HeaderFragment

class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HeaderFragment>(R.id.fragment_header)
            }

            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            val adapter = CustomAdapter()


            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter

            val recyclerViewSecond = findViewById<RecyclerView>(R.id.recyclerViewSecond)
            val adapterSecond = CustomAdapterSecond()

            recyclerViewSecond.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            recyclerViewSecond.adapter = adapterSecond
        }
    }
}