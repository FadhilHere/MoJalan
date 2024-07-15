package com.example.mojalan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TourGuideAdapter(
    private val tourGuideList: List<TourGuide>,
    private val listener: OnTourGuideClickListener
) : RecyclerView.Adapter<TourGuideAdapter.TourGuideViewHolder>() {

    interface OnTourGuideClickListener {
        fun onTourGuideClick(tourGuide: TourGuide)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourGuideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tour_guide, parent, false)
        return TourGuideViewHolder(view)
    }

    override fun onBindViewHolder(holder: TourGuideViewHolder, position: Int) {
        val tourGuide = tourGuideList[position]

        // Set data to views
        holder.tourGuideName.text = tourGuide.name
        holder.tourGuideRating.text = tourGuide.rating
        holder.tourGuideTag1.text = tourGuide.tag1
        holder.tourGuideTag2.text = tourGuide.tag2
        holder.tourGuideLocation.text = tourGuide.location

        // Load image using Glide
        Glide.with(holder.itemView.context)
            .load(tourGuide.imageUrl)
            .into(holder.tourGuideImage)

        // Set click listener
        holder.itemView.setOnClickListener {
            listener.onTourGuideClick(tourGuide)
        }
    }

    override fun getItemCount(): Int {
        return tourGuideList.size
    }

    class TourGuideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tourGuideImage: ImageView = itemView.findViewById(R.id.tour_guide_image)
        val tourGuideName: TextView = itemView.findViewById(R.id.tour_guide_name)
        val tourGuideRating: TextView = itemView.findViewById(R.id.tour_guide_rating)
        val tourGuideTag1: TextView = itemView.findViewById(R.id.tour_guide_tag_1)
        val tourGuideTag2: TextView = itemView.findViewById(R.id.tour_guide_tag_2)
        val tourGuideLocation: TextView = itemView.findViewById(R.id.tour_guide_location)
    }
}
