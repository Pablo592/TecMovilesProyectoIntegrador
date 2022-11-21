package services

import retrofit2.Call
import retrofit2.http.GET
import services.dataClasses.ProductDTO

interface ProductService {

    @GET("https://mocki.io/v1/8b2d574a-cbd3-4515-a123-6a8a0d4a3369")
    fun getProductList() : Call<List<ProductDTO>>
}