package com.example.mojalan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mojalan.Fragment.DetailTourGuideFragment
import com.example.mojalan.Fragment.TempatWisataFragment

class BerandaFragment : Fragment(), TourGuideAdapter.OnTourGuideClickListener {

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
            TourGuide(
                name = "Budi Santoso",
                rating = "★★★★★",
                tag1 = "Alam",
                tag2 = "Kota",
                location = "Pekanbaru",
                description = "Deskripsi Budi Santoso",
                languages = "Indonesia, Inggris",
                price = "Rp 350.000,00",
                imageUrl = "D:\\Kuliah\\Semester 4\\Mobile\\MoJalan\\app\\src\\main\\res\\drawable\\galih.jpeg",
                experienceUrl = "D:\\Kuliah\\Semester 4\\Mobile\\MoJalan\\app\\src\\main\\res\\drawable\\galih.jpeg",
                customerCount = 10,
                tourCount = 5,
                totalEarnings = 1750000.0
            ),
            TourGuide(
                name = "Ahmad Hidayat",
                rating = "★★★★★",
                tag1 = "Alam",
                tag2 = "Kota",
                location = "Bali",
                description = "Deskripsi Ahmad Hidayat",
                languages = "Indonesia, Inggris",
                price = "Rp 350.000,00",
                imageUrl = "https://example.com/path/to/image2.jpg",
                experienceUrl = "https://example.com/path/to/experience2.jpg",
                customerCount = 12,
                tourCount = 7,
                totalEarnings = 2450000.0
            )
        ), this)
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

    override fun onTourGuideClick(tourGuide: TourGuide) {
        val detailFragment = DetailTourGuideFragment.newInstance(tourGuide)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}
