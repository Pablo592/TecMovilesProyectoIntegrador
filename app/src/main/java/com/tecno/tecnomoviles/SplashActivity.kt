package com.tecno.tecnomoviles

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
    lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        supportActionBar?.hide()

        val mainIntent = Intent(this, LoginActivity::class.java)

        Timer().schedule(timerTask {
            startActivity(mainIntent)
        }, 1500)

 //       productService = ProductRetrofit()
 //       getProductListFromServerOption2()
    }

    private fun getProductListFromServerOption2() {
        serviceResult = productService.productRetrofitService.getProductList()
        serviceResult.enqueue(object : Callback<List<ProductDTO>> {
            override fun onResponse(
                call: Call<List<ProductDTO>>,
                response: Response<List<ProductDTO>>
            ) {
                resultTextView.text = response.body().toString()
                response.body()?.let {
             //       data = it.listaProductos
                }
            //    resultTextView.text = data[1].toString()
                //hideLoader
            }

            override fun onFailure(call: Call<List<ProductDTO>>, t: Throwable) {
                resultTextView.text = t.message

            }
        })
    }
}

