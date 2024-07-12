package com.example.mojalan.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        adapter = TourGuideAdapter(listOf(
            TourGuide(R.drawable.galih, "Budi Santoso", "★★★★★", "Alam", "Kota", "Pekanbaru"),
            TourGuide(R.drawable.galih, "Ahmad Hidayat", "★★★★★", "Alam", "Kota", "Bali"),
            // Tambahkan data lainnya di sini
        ))
        recyclerView.adapter = adapter

        return view
    }
}
