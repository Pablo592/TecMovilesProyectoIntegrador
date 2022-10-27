package com.tecno.tecnomoviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapterHistorialCompra: RecyclerView.Adapter<CustomAdapterHistorialCompra.ViewHolder>() {

    val titles = arrayOf("Wolfang SA-901",
        "Cooler Master Blue",
        "Cougar Minos",
        "Nvidia RTX 3080 Ti"
    )

    val date = arrayOf("Fecha: 21/03/2022",
        "Fecha: 03/05/2022",
        "Fecha: 10/06/2022",
        "Fecha: 17/08/2022"
    )

    val images = intArrayOf(R.drawable.auricular,
        R.drawable.gabinete,
        R.drawable.mouse,
        R.drawable.tarjeta_video)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.historial_compra,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemDate.text = date[i]
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDate: TextView

        init {
            itemImage = itemView.findViewById(R.id.productImageHistory)
            itemTitle = itemView.findViewById(R.id.titleDateText)
            itemDate = itemView.findViewById(R.id.dateText)
        }
    }
}