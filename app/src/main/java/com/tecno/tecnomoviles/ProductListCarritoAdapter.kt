package com.tecno.tecnomoviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.MyApplication
import com.bumptech.glide.Glide
import kotlinx.coroutines.runBlocking
import persistence.entitys.product.Product

class ProductListCarritoAdapter(private val data: List<Product>, private val listener: CarritoActivity) : RecyclerView.Adapter<ProductListCarritoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListCarritoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.producto_carrito, parent, false)
        return ProductListCarritoViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ProductListCarritoViewHolder, position: Int) {
        val product = data[position]
        holder.productTitle.text = product.name
        holder.productPrice.text = "Precio      $" +  product.price.toString()
        Glide
            .with(holder.itemView)
            .load(product.urlPhoto)
            .centerCrop()
            .into(holder.productImage)
        holder.productErase.setOnClickListener(){
            MyApplication.preferences.setRefreshActivity(true)
            updateProducts(product)
        }

    }

    override fun getItemCount(): Int = data.size
}

class ProductListCarritoViewHolder(itemView: View, listener: CarritoActivity): RecyclerView.ViewHolder(itemView) {
    var productTitle: TextView = itemView.findViewById(R.id.titleProductText)
    var productImage: ImageView = itemView.findViewById(R.id.productImage)
    var productPrice: TextView =  itemView.findViewById(R.id.priceText)
    var productErase: ImageView =  itemView.findViewById(R.id.eraseButton)

    init {
        itemView.setOnClickListener {
            listener.onItemCarritoClick(adapterPosition)
        }

        productErase.setOnClickListener(){
            listener.onItemCarritoClick(adapterPosition)
        }
        }

}

private fun updateProducts(product: Product){
    runBlocking {
        MyApplication.myAppDatabase.productDao().updateProduct(
            Product(
                id = product.id,
                name = product.name,
                type = product.type,
                urlPhoto = product.urlPhoto,
                price = product.price,
                description = product.description,
                features = product.features,
                trolley = false,
                recommended = product.recommended,
                bought = product.bought
            )
        )
    }
}


interface ProductListOnClickCarritoListener {
    fun onItemCarritoClick(position: Int)
}