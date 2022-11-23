package com.tecno.tecnomoviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import persistence.entitys.product.Product

class ProductListAdapter(private val data: List<Product>, private val listener: HomeActivity) : RecyclerView.Adapter<ProductListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ProductListViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = data[position]
        holder.productTitle.text = product.name
        Glide
            .with(holder.itemView)
            .load(product.urlPhoto)
            .centerCrop()
            .into(holder.productImage)

    }

    override fun getItemCount(): Int = data.size
}

class ProductListViewHolder(itemView: View, listener: ProductListOnClickListener): RecyclerView.ViewHolder(itemView) {
    var productTitle: TextView = itemView.findViewById(R.id.item_title)
    var productImage: ImageView = itemView.findViewById(R.id.item_image)

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}

interface ProductListOnClickListener {
    fun onItemClick(position: Int)
}