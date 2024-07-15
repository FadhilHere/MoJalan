package com.example.mojalan.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mojalan.TourGuide
import com.example.mojalan.databinding.FragmentDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi Firebase
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        // Ambil ID user yang sedang login
        val currentUser = auth.currentUser
        val userId = currentUser?.uid

        // Ambil data user dari Firebase berdasarkan user ID
        if (userId != null) {
            database.child("users").child(userId).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val userRole = dataSnapshot.child("role").getValue(String::class.java)
                    if (userRole == "Tour Guide") {
                        // Ambil data tour guide dari Firebase berdasarkan user ID
                        database.child("tourGuides").child(userId).addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(tourGuideSnapshot: DataSnapshot) {
                                val tourGuide = tourGuideSnapshot.getValue(TourGuide::class.java)
                                tourGuide?.let {
                                    updateUI(it)
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                // Handle possible errors.
                            }
                        })
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle possible errors.
                }
            })
        }
    }

    private fun updateUI(tourGuide: TourGuide) {
        // Update UI dengan data tour guide
        Glide.with(this)
            .load(tourGuide.imageUrl)
            .into(binding.imgProfile)

        binding.tvName.text = tourGuide.name
        binding.tvDescription.text = tourGuide.description
        binding.tvLanguages.text = "Bahasa: ${tourGuide.languages}"
        binding.tvRegion.text = tourGuide.location
        binding.tvPrice.text = "Rp ${tourGuide.price},00 / hari"
        binding.tvJumlahPelanggan.text = tourGuide.customerCount.toString()
        binding.tvJumlahWisata.text = tourGuide.tourCount.toString()
        binding.tvTotalPendapatan.text = "Rp ${tourGuide.totalEarnings}"

        Glide.with(this)
            .load(tourGuide.experienceUrl)
            .into(binding.imgExperience)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
