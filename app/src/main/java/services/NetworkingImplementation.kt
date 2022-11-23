package services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkingImplementation {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://mocki.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val service : ProductService = retrofit.create(ProductService::class.java)
}