package com.tecno.tecnomoviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import persistence.entitys.product.Product
import java.util.Date

class ProductListHistorialCompraAdapter(private val data: List<Product>, private val listener: ProductListOnClickHistorialCompraListener) : RecyclerView.Adapter<ProductListHistorialCompraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListHistorialCompraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.historial_compra, parent, false)
        return ProductListHistorialCompraViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ProductListHistorialCompraViewHolder, position: Int) {
        val product = data[position]
        holder.productTitle.text = product.name
        holder.productDate.text = product.dateBought
        Glide
            .with(holder.itemView)
            .load(product.urlPhoto)
            .centerCrop()
            .into(holder.productImage)

    }

    override fun getItemCount(): Int = data.size
}

class ProductListHistorialCompraViewHolder(itemView: View, listener: ProductListOnClickHistorialCompraListener): RecyclerView.ViewHolder(itemView) {
    var productTitle: TextView = itemView.findViewById(R.id.titleDateText)
    var productDate: TextView = itemView.findViewById(R.id.dateText)
    var productImage: ImageView = itemView.findViewById(R.id.productImageHistory)

    init {
        itemView.setOnClickListener {
            listener.onItemHistorialCompraClick(adapterPosition)
        }
    }
}

interface ProductListOnClickHistorialCompraListener {
    fun onItemHistorialCompraClick(position: Int)
}