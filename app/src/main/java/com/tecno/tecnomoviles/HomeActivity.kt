package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.MyApplication
import com.tecno.tecnomoviles.databinding.HomeBinding
import com.tecno.tecnomoviles.fragments.HeaderFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.product.Product

class HomeActivity: AppCompatActivity() , ProductListOnClickListener, ProductListTypeOnClickListener,ProductListOnClickRecomendedListener {

    val productLiveData = MutableLiveData<List<Product>>()
    val productLiveTypeData = MutableLiveData<List<Product>>()
    val productLiveRecomendedData = MutableLiveData<List<Product>>()
    lateinit var listaProductos: List<Product>
    private lateinit var binding : HomeBinding
    lateinit var listaTypeProductos: List<Product>
    lateinit var listaRecomendedProductos: List<Product>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        supportActionBar?.hide()

        binding = HomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewType.visibility = View.INVISIBLE
        binding.botonReturn.visibility = View.INVISIBLE



        binding.botonReturn.setOnClickListener(){
            binding.recyclerViewType.visibility = View.INVISIBLE
            binding.botonReturn.visibility = View.INVISIBLE
            binding.recyclerView.visibility = View.VISIBLE
        }


        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HeaderFragment>(R.id.fragment_header)
            }
        }

        getListProducts()
        getListRecomendedProducts()
        MyApplication.preferences.setActivityName("HomeActivity")
    }

    override fun onResume() {
        super.onResume()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ProductListAdapter(data = listaProductos, listener = this)


/*
        val recyclerViewRecomended = findViewById<RecyclerView>(R.id.recyclerViewSecond)
        recyclerViewRecomended.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        recyclerViewRecomended.adapter = ProductListRecomendedAdapter(data = listaRecomendedProductos, listener = this)*/
    }

    private fun getProductsForDatabase() {
        runBlocking {
            launch {
                productLiveData.value = MyApplication.myAppDatabase.productDao().getOneType()
            }
        }
    }

    private fun getProductsForDatabaseType(type:String) {
        runBlocking {
            launch {
                productLiveTypeData.value = MyApplication.myAppDatabase.productDao().getType(type)
            }
        }
    }

    private fun getProductsForDatabaseRecomended() {
        runBlocking {
            launch {
                if(MyApplication.myAppDatabase.productDao().getRecommendedIsEmpty(true) > 0){
                    productLiveRecomendedData.value = MyApplication.myAppDatabase.productDao().getRecommended(true)
                }else{
                    productLiveRecomendedData.value = MyApplication.myAppDatabase.productDao().getOneType()
                }
            }
        }
    }

    public fun getListRecomendedProducts(){
        getProductsForDatabaseRecomended()
        productLiveRecomendedData.observe(this, Observer{
            listaRecomendedProductos =  it
            val recyclerViewRecomended = findViewById<RecyclerView>(R.id.recyclerViewSecond)
            recyclerViewRecomended.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
            recyclerViewRecomended.adapter = ProductListRecomendedAdapter(data = listaRecomendedProductos, listener = this)
        })
    }

    public fun getListTypeProducts(type:String){
        getProductsForDatabaseType(type)
        productLiveTypeData.observe(this, Observer{
            listaTypeProductos =  it
        })
    }

    public fun getListProducts(){
        getProductsForDatabase()
        productLiveData.observe(this, Observer{
            listaProductos =  it
        })

    }

    override fun onItemClick(position: Int) {
        getListTypeProducts(listaProductos[position].type)
        val recyclerViewType = findViewById<RecyclerView>(R.id.recyclerViewType)
        recyclerViewType.layoutManager = LinearLayoutManager(this)
        recyclerViewType.adapter = ProductListTypeAdapter(data = listaTypeProductos, listener = this)
        binding.recyclerView.visibility = View.INVISIBLE
        binding.recyclerViewType.visibility = View.VISIBLE
        binding.botonReturn.visibility = View.VISIBLE
    }

    override fun onItemTypeClick(position: Int) {
        Toast.makeText(baseContext, "Su Producto seleccionado es: ${listaTypeProductos[position].name}", Toast.LENGTH_SHORT).show()
        val myIntent = Intent(this, DetailedActivity::class.java)
        myIntent.putExtra("product",listaTypeProductos[position].id)
        startActivity(myIntent)
    }

    override fun onItemRecomendedClick(position: Int) {
        Toast.makeText(baseContext, "Su Producto seleccionado es: ${listaProductos[position].name}", Toast.LENGTH_SHORT).show()
        val myIntent = Intent(this, DetailedActivity::class.java)
        myIntent.putExtra("product",listaProductos[position].id)
        startActivity(myIntent)
    }

}