package com.tecno.tecnomoviles


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var onItemClick: ((Producto)-> Unit)? = null


    val titles = arrayOf("Procesadores",
        "Memoria RAM",
        "Monitores",
        "Tarjetas Gráficas"
        )

    val images = intArrayOf(R.drawable.procesadores,
    R.drawable.ram,
    R.drawable.monitores,
    R.drawable.tarjeta_video)

    val imagesProduct = intArrayOf(R.drawable.procesador1_producto,
        R.drawable.ram1_producto,
        R.drawable.monitor1_producto,
        R.drawable.grafica2_producto
        )

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        images[i]
        val producto = Producto(imagesProduct[i])

        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemView.setOnClickListener() {
            onItemClick?.invoke(producto)
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
        }
    }
}