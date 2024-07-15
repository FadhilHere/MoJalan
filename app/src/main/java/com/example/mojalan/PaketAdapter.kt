package com.example.mojalan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PaketAdapter(private val items: List<Paket>) : RecyclerView.Adapter<PaketAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.paket_image)
        val nameView: TextView = itemView.findViewById(R.id.paket_name)
        val priceView: TextView = itemView.findViewById(R.id.paket_price)
        val locationView: TextView = itemView.findViewById(R.id.paket_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_paket, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.nameView.text = item.name
        holder.priceView.text = item.price
        holder.locationView.text = item.location
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
