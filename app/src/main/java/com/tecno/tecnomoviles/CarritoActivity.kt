package com.tecno.tecnomoviles

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.MyApplication
import com.tecno.tecnomoviles.databinding.ActivityCarritoBinding
import com.tecno.tecnomoviles.databinding.LoginBinding
import com.tecno.tecnomoviles.fragments.HeaderFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.product.Product
import java.util.*

class CarritoActivity : AppCompatActivity() , ProductListOnClickCarritoListener{

    val productLiveData = MutableLiveData<List<Product>>()
    lateinit var listaProductos: List<Product>
    private lateinit var binding : ActivityCarritoBinding
    var totalPrecio:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)
        supportActionBar?.hide()

        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HeaderFragment>(R.id.fragment_header)
            }
        }
        getListProducts()
        MyApplication.preferences.setActivityName("CarritoActivity")

        binding.comprar.setOnClickListener(){

            for (j in listaProductos) {
                j.dateBought =  Date().toString().split("GMT")[0]
                updateProducts(j)
            }

            startActivity(Intent(this, ConfirmacionCompra::class.java))
        }

        binding.cancelar.setOnClickListener(){
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }


    private fun getProductsForDatabaseCarrito() {
        runBlocking {
            launch {
                productLiveData.value = MyApplication.myAppDatabase.productDao().getTrolley(true)
            }
        }
    }

    public fun getListProducts(){
        getProductsForDatabaseCarrito()
        productLiveData.observe(this, Observer{
            listaProductos =  it

            for (j in listaProductos) {
                totalPrecio += j.price
            }

            binding.precioTotal.text = "$"+totalPrecio.toString()

            if(listaProductos.isNotEmpty()){
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewCarrito)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = ProductListCarritoAdapter(data = listaProductos, listener = this)
            }else{
                binding.comprar.visibility = View.INVISIBLE
                binding.cancelar.visibility = View.INVISIBLE
                binding.totalPagar.visibility = View.INVISIBLE
                binding.precioTotal.visibility = View.INVISIBLE
                binding.productosInexistentes.visibility = View.VISIBLE
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
                    bought = true,
                    dateBought = product.dateBought
                )
            )
        }
    }

    override fun onItemCarritoClick(position: Int) {
        if(MyApplication.preferences.getRefreshActivity() == "true"){
            startActivity(Intent(this, CarritoActivity::class.java))
            MyApplication.preferences.setRefreshActivity(false)
        }
    }
}