package com.example.mojalan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TourGuideAdapter(private val items: List<TourGuide>) : RecyclerView.Adapter<TourGuideAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.tour_guide_image)
        val nameView: TextView = itemView.findViewById(R.id.tour_guide_name)
        val ratingView: TextView = itemView.findViewById(R.id.tour_guide_rating)
        val tag1View: TextView = itemView.findViewById(R.id.tour_guide_tag_1)
        val tag2View: TextView = itemView.findViewById(R.id.tour_guide_tag_2)
        val locationView: TextView = itemView.findViewById(R.id.tour_guide_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tour_guide, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.nameView.text = item.name
        holder.ratingView.text = item.rating
        holder.tag1View.text = item.tag1
        holder.tag2View.text = item.tag2
        holder.locationView.text = item.location
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

data class TourGuide(
    val imageResId: Int,
    val name: String,
    val rating: String,
    val tag1: String,
    val tag2: String,
    val location: String
)
