package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.MyApplication
import com.tecno.tecnomoviles.databinding.HomeBinding
import com.tecno.tecnomoviles.databinding.ProfileBinding
import com.tecno.tecnomoviles.fragments.HeaderFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.product.Product
import persistence.entitys.user.User

class HomeActivity: AppCompatActivity() {

    val productLiveData = MutableLiveData<List<Product>>()
    lateinit var listaProductos: MutableList<List<Product>!>


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

            val recyclerViewSecond = findViewById<RecyclerView>(R.id.recyclerViewSecond)
            val adapterSecond = CustomAdapterSecond()

            recyclerViewSecond.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
            recyclerViewSecond.adapter = adapterSecond
            adapterSecond.onItemClick = {
                val intent = Intent(this, DetailedActivity::class.java)
                intent.putExtra("producto",it)
                startActivity(intent)
            }

        }
        MyApplication.preferences.setActivityName("HomeActivity")
    }

    private fun getProductsForDatabase() {
        runBlocking {
            launch {
                    productLiveData.value = MyApplication.myAppDatabase.productDao().getAllProduct()
            }
        }
    }

    public fun getListProducts(): MutableList<List<Product>> {
        getProductsForDatabase()
        productLiveData.observe(this, Observer{
            listaProductos =   mutableListOf(it)
        })
        return listaProductos
    }


}