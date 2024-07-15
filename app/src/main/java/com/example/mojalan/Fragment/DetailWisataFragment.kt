package com.example.mojalan.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mojalan.R
import android.widget.ImageView
import android.widget.TextView

class DetailWisataFragment : Fragment() {

    companion object {
        private const val ARG_IMAGE_RES_ID = "image_res_id"
        private const val ARG_NAME = "name"
        private const val ARG_LOCATION = "location"
        private const val ARG_RATING = "rating"
        private const val ARG_DISTANCE = "distance"
        private const val ARG_RESTAURANTS = "restaurants"
        private const val ARG_DESCRIPTION = "description"

        fun newInstance(imageResId: Int, name: String, location: String, rating: String, distance: String, restaurants: String, description: String): DetailWisataFragment {
            val fragment = DetailWisataFragment()
            val args = Bundle()
            args.putInt(ARG_IMAGE_RES_ID, imageResId)
            args.putString(ARG_NAME, name)
            args.putString(ARG_LOCATION, location)
            args.putString(ARG_RATING, rating)
            args.putString(ARG_DISTANCE, distance)
            args.putString(ARG_RESTAURANTS, restaurants)
            args.putString(ARG_DESCRIPTION, description)
            fragment.arguments = args
            return fragment
        }
    }

    private var imageResId: Int = 0
    private lateinit var name: String
    private lateinit var location: String
    private lateinit var rating: String
    private lateinit var distance: String
    private lateinit var restaurants: String
    private lateinit var description: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageResId = it.getInt(ARG_IMAGE_RES_ID)
            name = it.getString(ARG_NAME).orEmpty()
            location = it.getString(ARG_LOCATION).orEmpty()
            rating = it.getString(ARG_RATING).orEmpty()
            distance = it.getString(ARG_DISTANCE).orEmpty()
            restaurants = it.getString(ARG_RESTAURANTS).orEmpty()
            description = it.getString(ARG_DESCRIPTION).orEmpty()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_wisata, container, false)

        val imageView: ImageView = view.findViewById(R.id.detail_image)
        val nameView: TextView = view.findViewById(R.id.detail_name)
        val locationView: TextView = view.findViewById(R.id.detail_location)
        val ratingView: TextView = view.findViewById(R.id.detail_rating)
        val distanceView: TextView = view.findViewById(R.id.detail_distance)
        val restaurantsView: TextView = view.findViewById(R.id.detail_restaurants)
        val descriptionView: TextView = view.findViewById(R.id.detail_description)
        val backButton: ImageView = view.findViewById(R.id.back_button)

        imageView.setImageResource(imageResId)
        nameView.text = name
        locationView.text = location
        ratingView.text = rating
        distanceView.text = distance
        restaurantsView.text = restaurants
        descriptionView.text = description

        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}
