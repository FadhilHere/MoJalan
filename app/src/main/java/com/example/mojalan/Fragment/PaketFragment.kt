package com.example.mojalan.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mojalan.Paket
import com.example.mojalan.PaketAdapter
import com.example.mojalan.R

class PaketFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PaketAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_paket, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = PaketAdapter(listOf(
            Paket(R.drawable.popular1, "Jalan - Jalan ke Bali", "Rp 1.000.000,00", "Riau - Bali"),
            Paket(R.drawable.popular2, "Jalan - Jalan ke Malang", "Rp 1.000.000,00", "Riau - Malang"),
            // Tambahkan data lainnya di sini
        ))
        recyclerView.adapter = adapter

        return view
    }
}
