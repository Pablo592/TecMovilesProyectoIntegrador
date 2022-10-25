package com.tecno.tecnomoviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapterProductoCarrito: RecyclerView.Adapter<CustomAdapterProductoCarrito.ViewHolder>() {

    val titles = arrayOf("Wolfang SA-901",
        "Cooler Master Blue",
        "Cougar Minos",
        "Nvidia RTX 3080 Ti"
    )

    val prices = arrayOf("Precio: $8500",
        "Precio: $3000",
        "Precio: $2500",
        "Precio: $350.000"
        )

    val images = intArrayOf(R.drawable.auricular,
        R.drawable.gabinete,
        R.drawable.mouse,
        R.drawable.tarjeta_video)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.producto_carrito,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemPrice.text = prices[i]
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrice: TextView

        init {
            itemImage = itemView.findViewById(R.id.productImage)
            itemTitle = itemView.findViewById(R.id.titleProductText)
            itemPrice = itemView.findViewById(R.id.priceText)
        }
    }
}