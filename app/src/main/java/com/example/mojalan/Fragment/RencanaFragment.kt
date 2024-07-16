package com.example.mojalan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class RencanaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menginfal layout fragment_rencana1
        return inflater.inflate(R.layout.rencana1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menggunakan findViewById pada view yang dihasilkan
        val button = view.findViewById<Button>(R.id.login_button)

        // Menetapkan reaksi saat button diklik
        button.setOnClickListener {
            val next = Intent(requireContext(), Rencana2::class.java)
            startActivity(next)
        }
    }
}