package com.tecno.tecnomoviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import persistence.entitys.product.Product

class ProductListRecomendedAdapter(private val data: List<Product>, private val listener: ProductListOnClickRecomendedListener) : RecyclerView.Adapter<ProductListRecomendedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListRecomendedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_image_layout, parent, false)
        return ProductListRecomendedViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ProductListRecomendedViewHolder, position: Int) {
        val product = data[position]
        Glide
            .with(holder.itemView)
            .load(product.urlPhoto)
            .centerCrop()
            .into(holder.productImage)

    }

    override fun getItemCount(): Int = data.size
}

class ProductListRecomendedViewHolder(itemView: View, listener: ProductListOnClickRecomendedListener): RecyclerView.ViewHolder(itemView) {
    var productImage: ImageView = itemView.findViewById(R.id.item_image_second)

    init {
        itemView.setOnClickListener {
            listener.onItemRecomendedClick(adapterPosition)
        }
    }
}

interface ProductListOnClickRecomendedListener {
    fun onItemRecomendedClick(position: Int)
}