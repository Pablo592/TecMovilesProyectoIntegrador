package services.dataClasses



data class ProductDTO (
    var id : Int? = null,
    val name: String,
    val type: String,
    val urlPhoto: String,
    val price: Double,
    val description: String,
    val features: String,
    val trolley: Boolean,
    val recommended: Boolean,
    val bought: Boolean
)