package com.tecno.tecnomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.MyApplication
import com.bumptech.glide.Glide
import com.tecno.tecnomoviles.databinding.ActivityDetailedBinding
import com.tecno.tecnomoviles.fragments.HeaderFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import persistence.entitys.product.Product
import kotlin.properties.Delegates

class DetailedActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailedBinding
    val productLiveData = MutableLiveData<Product>()
    lateinit var producto: Product
    var id by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<HeaderFragment>(R.id.fragment_header)
            }
        }

        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent?.extras?.get("product") as Int


        getProduct()


        binding.comprar.setOnClickListener(){
            producto.bought = true
            updateProducts(producto)
            startActivity(Intent(this, ConfirmacionCompra::class.java))
        }
        binding.aniadirAlCarro.setOnClickListener(){
            producto.trolley = true
            updateProducts(producto)
            startActivity(Intent(this, CarritoActivity::class.java))
        }
        MyApplication.preferences.setActivityName("DetailedActivity")
    }

    override fun onResume() {
        super.onResume()
        updateProducts(producto)
        binding.descripcion.text = producto.name
        binding.precio.text = "$" + producto.price.toString()
        binding.caracteristicas.text = producto.features
        Glide.with(this).load(producto.urlPhoto).into(binding.detailedActivityImage)
    }

    public fun getProduct(){
        getProductsForDatabase()
        productLiveData.observe(this, Observer{
            producto =  it
            producto.recommended = true
        })
    }

    private fun getProductsForDatabase() {
        runBlocking {
            launch {
                productLiveData.value = MyApplication.myAppDatabase.productDao().getProductById(id)
            }
        }
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
                    trolley = product.trolley,
                    recommended = product.recommended,
                    bought = product.bought
                )
            )
        }
    }
}