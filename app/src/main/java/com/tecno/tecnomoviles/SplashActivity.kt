package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.MyApplication
import com.tecno.tecnomoviles.databinding.SplashBinding
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

    //lateinit var productService: ProductRetrofit
    //lateinit var serviceResult: Call<MutableList<ProductDTO>>
    //lateinit var listaProductos: MutableList<ProductDTO>
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

        //productService = ProductRetrofit()
        //getProductListFromServerOption2()
    }

/*
private fun getProductListFromServerOption2() {
    serviceResult = productService.productRetrofitService.getProductList()
    serviceResult.enqueue(object : Callback<MutableList<ProductDTO>> {
        override fun onResponse(
            call: Call<MutableList<ProductDTO>>,
            response: Response<MutableList<ProductDTO>>
        ) {
            binding.userText.text = response.body().toString()
            response.body()?.let {
                listaProductos = it
            }
            for (j in listaProductos) {
                saveProducts(j)
            }
        }
        override fun onFailure(call: Call<MutableList<ProductDTO>>, t: Throwable) {
            binding.userText.text = t.message

        }
    })
}
*/


/*
private fun saveProducts(product: ProductDTO){
    runBlocking {
        MyApplication.myAppDatabase.productDao().setProduct(
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
*/

}

