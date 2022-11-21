package com.tecno.tecnomoviles.model


import com.google.gson.annotations.SerializedName

data class productJsonItem(
    @SerializedName("bought")
    val bought: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("features")
    val features: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("recommended")
    val recommended: Boolean,
    @SerializedName("trolley")
    val trolley: Boolean,
    @SerializedName("type")
    val type: String,
    @SerializedName("urlPhoto")
    val urlPhoto: String
)