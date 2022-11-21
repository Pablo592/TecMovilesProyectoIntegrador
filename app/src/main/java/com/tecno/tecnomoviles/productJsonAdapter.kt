package com.tecno.tecnomoviles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.NonDisposableHandle.parent
import services.dataClasses.ProductDTO


class productJsonAdapter(val productDTO: MutableList<ProductDTO>): RecyclerView.Adapter<productJsonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productJsonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_image_layout , parent , false)
        return productJsonViewHolder(view)
    }

    override fun onBindViewHolder(holder: productJsonViewHolder, position: Int) {
        val product = productDTO[position]
         Glide.with(holder.itemView).load(product.thumbnailUrl).into(holder.imageProduct);

        //return holder.cardImage.setImageResource(productDTO[position])

    }

    override fun getItemCount(): Int {
        return productDTO.size
    }

}

class productJsonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    val imageProduct : ImageView = itemView.findViewById(R.id.item_image_second)


}


