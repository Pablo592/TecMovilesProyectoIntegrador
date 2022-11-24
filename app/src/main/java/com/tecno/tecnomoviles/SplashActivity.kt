package com.tecno.tecnomoviles

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.MyApplication
import com.tecno.tecnomoviles.databinding.SplashBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    private fun getProductListFromServerOption2() {
        serviceResult = productService.productRetrofitService.getProductList()
        serviceResult.enqueue(object : Callback<List<ProductDTO>> {
            override fun onResponse(
                call: Call<List<ProductDTO>>,
                response: Response<List<ProductDTO>>
            ) {
                response.body()?.let {

                    for (j in it) {
                        getProfileForDatabase(j)
                    }
                }
            }
            override fun onFailure(call: Call<List<ProductDTO>>, t: Throwable) {

            }
        })
    }

    private fun getProfileForDatabase(product: ProductDTO) {
        runBlocking {
            launch {
                if (MyApplication.myAppDatabase.productDao().isEmpty(product.name) > 0) {
                    updateProducts(product,MyApplication.myAppDatabase.productDao().getProductIdByName(product.name))
                }else{
                    saveProducts(product)
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

    private fun updateProducts(product: ProductDTO, id:Int){
        runBlocking {
            MyApplication.myAppDatabase.productDao().updateProduct(
                Product(
                    id = id,
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
