package services

import retrofit2.http.GET
import services.dataClasses.ProductDTO

interface ProductService {

    @GET("")
    fun getProductList() : List<ProductDTO>
}