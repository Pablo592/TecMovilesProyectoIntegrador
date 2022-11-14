package services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRetrofit {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://mocki.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val productService:ProductService = retrofit.create(ProductService::class.java)
}