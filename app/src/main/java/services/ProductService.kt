package services

import retrofit2.Call
import retrofit2.http.GET
import services.dataClasses.ProductDTO

interface ProductService {

    @GET("https://mocki.io/v1/03f1485c-0795-47ae-93bb-a11d976152c5")
    fun getProductList() : Call<List<ProductDTO>>
}