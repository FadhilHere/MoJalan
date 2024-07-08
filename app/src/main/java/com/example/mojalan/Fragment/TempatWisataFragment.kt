package com.example.mojalan.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mojalan.R
import com.example.mojalan.TempatWisata
import com.example.mojalan.TempatWisataAdapter

class TempatWisataFragment : Fragment() {

    private lateinit var recyclerViewTempatWisata: RecyclerView
    private lateinit var tempatWisataAdapter: TempatWisataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tempat_wisata, container, false)
        val backButton: ImageView = view.findViewById(R.id.back_button)
        recyclerViewTempatWisata = view.findViewById(R.id.recycler_view_tempat_wisata)
        recyclerViewTempatWisata.layoutManager = GridLayoutManager(context, 2)
        tempatWisataAdapter = TempatWisataAdapter(listOf(
            TempatWisata(R.drawable.popular1, "Gunung Bromo"),
            TempatWisata(R.drawable.popular2, "Pulau Nusa Penida"),
            TempatWisata(R.drawable.popular3, "Candi Borobudur")
            // Tambahkan item lainnya di sini
        )) { tempatWisata ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailWisataFragment.newInstance(
                    tempatWisata.imageResId,
                    tempatWisata.name,
                    "Bali, Indonesia",  // Location Example
                    "4.8 (1k)",  // Rating Example
                    "1000 km",  // Distance Example
                    "100 tersedia",  // Restaurants Example
                    "Deskripsi tempat wisata..."  // Description Example
                ))
                .addToBackStack(null)
                .commit()
        }
        recyclerViewTempatWisata.adapter = tempatWisataAdapter

        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return view
    }
}
