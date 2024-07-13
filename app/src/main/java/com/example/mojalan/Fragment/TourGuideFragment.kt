package com.example.mojalan.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mojalan.Comment
import com.example.mojalan.R
import com.example.mojalan.TourGuide
import com.example.mojalan.TourGuideAdapter

class TourGuideFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TourGuideAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tour_guide, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_tour_guide)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Creating instances of TourGuide with all parameters
        adapter = TourGuideAdapter(
            listOf(
                TourGuide(
                    imageResId = R.drawable.galih,
                    name = "Budi Santoso",
                    rating = "★★★★★",
                    tag1 = "Alam",
                    tag2 = "Kota",
                    location = "Pekanbaru",
                    description = "Deskripsi Budi Santoso",
                    languages = "Indonesia, Inggris",
                    price = "Rp 350.000,00",
                    experience = listOf(R.drawable.popular1, R.drawable.popular2),
                    comments = listOf(
                        Comment("User1", "Komentar 1"),
                        Comment("User2", "Komentar 2")
                    )
                ),
                TourGuide(
                    imageResId = R.drawable.galih,
                    name = "Ahmad Hidayat",
                    rating = "★★★★★",
                    tag1 = "Alam",
                    tag2 = "Kota",
                    location = "Bali",
                    description = "Deskripsi Ahmad Hidayat",
                    languages = "Indonesia, Inggris",
                    price = "Rp 350.000,00",
                    experience = listOf(R.drawable.popular1, R.drawable.popular2),
                    comments = listOf(
                        Comment("User3", "Komentar 3"),
                        Comment("User4", "Komentar 4")
                    )
                )
            ),
            activity = requireActivity()
        )

        recyclerView.adapter = adapter

        return view
    }
}
