package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.MyApplication
import com.tecno.tecnomoviles.fragments.HeaderFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.product.Product


class HomeActivity: AppCompatActivity(), ProductListOnClickListener {

    private val productList = createProducts()
    private val productLiveData = MutableLiveData<Product>()
    lateinit var listaProductos: List<Product>

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
            adapter.onItemClick = {
                val intent = Intent(this, DetailedActivity::class.java)
                intent.putExtra("producto",it)
                startActivity(intent)
            }

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter

            /*
            val recyclerViewSecond = findViewById<RecyclerView>(R.id.recyclerViewSecond)
            val adapterSecond = CustomAdapterSecond()

            recyclerViewSecond.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            recyclerViewSecond.adapter = adapterSecond
            adapterSecond.onItemClick = {
                val intent = Intent(this, DetailedActivity::class.java)
                intent.putExtra("producto",it)
                startActivity(intent)
            }
            */


            getProfileForDatabase()
            productLiveData.observe(this, Observer{
                listaProductos = listOf(it) }
            )



            val recyclerViewSecond = findViewById<RecyclerView>(R.id.recyclerViewSecond)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = productJsonAdapter(data = listaProductos, listener = this)

        }
        MyApplication.preferences.setActivityName("HomeActivity")
    }

    private fun createProducts() = mutableListOf<Product>(
        Product(urlPhoto = "https://i.pinimg.com/564x/e9/d4/31/e9d431b1d18417583fe8388b85984c65.jpg" , bought = false , recommended = false ,
            features = "si" , description = "si" , price = 300.00 , type = "si" , trolley = false , name = "si" , id = 1)
    )

    private fun getProfileForDatabase() {
        runBlocking {
            launch {
                productLiveData.value = MyApplication.myAppDatabase.productDao().getType("Auriculares")
            }
        }
    }

    override fun onItemClick(position: Int) {

        val myIntent = Intent(this, DetailedActivity::class.java)
        startActivity(myIntent)

    }



}