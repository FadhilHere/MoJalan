package com.example.mojalan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mojalan.Fragment.TempatWisataFragment


class BerandaFragment : Fragment() {

    private lateinit var recyclerViewLiburan: RecyclerView
    private lateinit var liburanAdapter: RekomendasiLiburanAdapter

    private lateinit var recyclerViewTourGuide: RecyclerView
    private lateinit var tourGuideAdapter: TourGuideAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_beranda, container, false)

        // Inisialisasi RecyclerView untuk rekomendasi liburan
        recyclerViewLiburan = view.findViewById(R.id.recycler_view_rekomendasi_liburan)
        recyclerViewLiburan.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        liburanAdapter = RekomendasiLiburanAdapter(listOf(
            R.drawable.ic_liburan_image,
            R.drawable.ic_liburan_image2,
            R.drawable.ic_liburan_image3
        ))
        recyclerViewLiburan.adapter = liburanAdapter

        // Inisialisasi RecyclerView untuk rekomendasi tour guide
        recyclerViewTourGuide = view.findViewById(R.id.recycler_view_tour_guide)
        recyclerViewTourGuide.layoutManager = LinearLayoutManager(context)
        tourGuideAdapter = TourGuideAdapter(listOf(
            TourGuide(R.drawable.galih, "Galih M Zimran", "★★★★★", "Alam", "Kota", "Tangerang"),
            TourGuide(R.drawable.galih, "Galih M Zimran", "★★★★★", "Alam", "Kota", "Tangerang"),
            TourGuide(R.drawable.galih, "Galih M Zimran", "★★★★★", "Alam", "Kota", "Tangerang")
        ))
        recyclerViewTourGuide.adapter = tourGuideAdapter

        // Tambahkan listener untuk "Lihat Semua"
        val lihatSemuaTextView: TextView = view.findViewById(R.id.lihat_semua)
        lihatSemuaTextView.setOnClickListener {
            // Navigasi ke TempatWisataFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TempatWisataFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
