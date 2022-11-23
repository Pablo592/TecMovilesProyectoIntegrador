package com.tecno.tecnomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.MyApplication
import com.tecno.tecnomoviles.databinding.EditDataUserBinding
import com.tecno.tecnomoviles.databinding.HistorialCompraBinding
import com.tecno.tecnomoviles.fragments.HeaderFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.product.Product

class HistorialCompra : AppCompatActivity(),ProductListOnClickHistorialCompraListener {

    val productLiveData = MutableLiveData<List<Product>>()
    lateinit var listaProductos: List<Product>


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

        getListProducts()
        MyApplication.preferences.setActivityName("HistorialCompra")
    }

    private fun getProductsForDatabaseHistorialCompra() {
        runBlocking {
            launch {
                productLiveData.value = MyApplication.myAppDatabase.productDao().getBought(true)
            }
        }
    }


    public fun getListProducts(){
        getProductsForDatabaseHistorialCompra()
        productLiveData.observe(this, Observer{
            listaProductos =  it

            if(listaProductos.isNotEmpty()){
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewHistory)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = ProductListHistorialCompraAdapter(data = listaProductos, listener = this)
            }

        })
    }

    private fun updateProducts(product: Product){
        runBlocking {
            MyApplication.myAppDatabase.productDao().updateProduct(
                Product(
                    id = product.id,
                    name = product.name,
                    type = product.type,
                    urlPhoto = product.urlPhoto,
                    price = product.price,
                    description = product.description,
                    features = product.features,
                    trolley = false,
                    recommended = product.recommended,
                    bought = true
                )
            )
        }
    }

    override fun onItemHistorialCompraClick(position: Int) {
        Toast.makeText(baseContext, "Su Producto seleccionado es: ${listaProductos[position].name}", Toast.LENGTH_SHORT).show()
        val myIntent = Intent(this, DetailedActivity::class.java)
        myIntent.putExtra("product",listaProductos[position].id)
        startActivity(myIntent)
    }



}