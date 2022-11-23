package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.MyApplication
import com.tecno.tecnomoviles.databinding.SplashBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import androidx.lifecycle.Observer
import persistence.entitys.product.Product
import services.ProductRetrofit
import services.dataClasses.ProductDTO
import java.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    lateinit var productService: ProductRetrofit
    lateinit var serviceResult: Call<List<ProductDTO>>
    lateinit var data: ProductDTO
    private lateinit var binding : SplashBinding
    var existente:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        supportActionBar?.hide()

        binding = SplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainIntent = Intent(this, LoginActivity::class.java)

        Timer().schedule(timerTask {
            startActivity(mainIntent)
        }, 1500)

        productService = ProductRetrofit()
        getProductListFromServerOption2()
    }

    override fun onResume() {
        super.onResume()

    /*    for (j in listaProductos) {
            getProfileForDatabase(j)

            if(existente){
                productLiveData.observe(this, Observer{
                    if((j.type != it.type) || (j.urlPhoto != it.urlPhoto)||
                        (j.price != it.price)|| (j.description != it.description)||
                        (j.features != it.features)|| (j.trolley != it.trolley)||
                        (j.recommended != it.recommended)|| (j.bought != it.bought)){

                        updateProducts(j)
                    }
                })
            }else{
                saveProducts(j)
            }
        }*/
    }

    private fun getProductListFromServerOption2() {
        serviceResult = productService.productRetrofitService.getProductList()
        serviceResult.enqueue(object : Callback<List<ProductDTO>> {
            override fun onResponse(
                call: Call<List<ProductDTO>>,
                response: Response<List<ProductDTO>>
            ) {
                binding.userText.text = response.body().toString()
                response.body()?.let { datos ->

                    for (j in datos) {

                        getProfileForDatabase(j)
                        if(existente){
                            updateProducts(j)
                        }else{
                            saveProducts(j)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<ProductDTO>>, t: Throwable) {
                binding.userText.text = t.message

            }
        })
    }

    private fun getProfileForDatabase(product: ProductDTO) {
        runBlocking {
            launch {
                    if (MyApplication.myAppDatabase.productDao().isEmpty(product.name) > 0) {
                        existente = true
                    }else{
                        existente = false
                    }
            }
        }
    }

    private fun saveProducts(product: ProductDTO){
        runBlocking {
            MyApplication.myAppDatabase.productDao().setProduct(
                Product(
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

    private fun updateProducts(product: ProductDTO){
        runBlocking {
            MyApplication.myAppDatabase.productDao().updateProduct(
                Product(
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
