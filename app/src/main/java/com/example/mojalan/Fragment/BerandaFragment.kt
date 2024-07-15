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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BerandaFragment : Fragment(), TourGuideAdapter.OnTourGuideClickListener {

    private lateinit var recyclerViewLiburan: RecyclerView
    private lateinit var liburanAdapter: RekomendasiLiburanAdapter

    private lateinit var recyclerViewTourGuide: RecyclerView
    private lateinit var tourGuideAdapter: TourGuideAdapter

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var usernameTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_beranda, container, false)

        // Inisialisasi Firebase
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        // Inisialisasi TextView untuk username
        usernameTextView = view.findViewById(R.id.username)

        // Ambil data user yang sedang login dan update UI
        val currentUser = auth.currentUser
        currentUser?.let {
            database.child("users").child(it.uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val username = snapshot.child("name").value.toString()
                    usernameTextView.text = "Hello, $username"
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle possible errors
                }
            })
        }

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

        // Ambil data Tour Guide dari Firebase
        database.child("tourGuides").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tourGuideList = mutableListOf<TourGuide>()
                for (dataSnapshot in snapshot.children) {
                    val tourGuide = dataSnapshot.getValue(TourGuide::class.java)
                    tourGuide?.let { tourGuideList.add(it) }
                }
                // Acak daftar Tour Guide
                tourGuideList.shuffle()
                // Batasi jumlah Tour Guide yang ditampilkan
                val limitedTourGuideList = tourGuideList.take(5)
                tourGuideAdapter = TourGuideAdapter(limitedTourGuideList, this@BerandaFragment)
                recyclerViewTourGuide.adapter = tourGuideAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors
            }
        })

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
