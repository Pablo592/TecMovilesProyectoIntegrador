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

class HomeActivity: AppCompatActivity() , ProductListOnClickListener {

    val productLiveData = MutableLiveData<List<Product>>()
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

            getListProducts()

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

    override fun onResume() {
        super.onResume()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductListAdapter(data = listaProductos, listener = this)
    }

    private fun getProductsForDatabase() {
        runBlocking {
            launch {
                    productLiveData.value = MyApplication.myAppDatabase.productDao().getOneType()
            }
        }
    }

    public fun getListProducts(){
        getProductsForDatabase()
        productLiveData.observe(this, Observer{
            listaProductos =  it
        })
    }

    override fun onItemClick(position: Int) {

        Toast.makeText(baseContext, "Su Producto seleccionado es: ${listaProductos[position].name}", Toast.LENGTH_SHORT).show()


        val myIntent = Intent(this, DetailedActivity::class.java)
        myIntent.putExtra("product",listaProductos[position].id)
        startActivity(myIntent)

    }

}