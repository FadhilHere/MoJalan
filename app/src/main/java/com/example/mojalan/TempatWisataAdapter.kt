package com.example.mojalan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TempatWisataAdapter(
    private val items: List<TempatWisata>,
    private val itemClickListener: (TempatWisata) -> Unit
) : RecyclerView.Adapter<TempatWisataAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_tempat_wisata)
        val nameView: TextView = itemView.findViewById(R.id.text_tempat_wisata)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tempat_wisata, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.nameView.text = item.name
        holder.itemView.setOnClickListener { itemClickListener(item) }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

data class TempatWisata(
    val imageResId: Int,
    val name: String
)
