package com.example.mojalan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mojalan.databinding.Rencana4Binding

class Rencana4Activity : AppCompatActivity() {
    private lateinit var binding: Rencana4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Rencana4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            // Code to add a new trip
        }

        binding.closeButton.setOnClickListener {
            // Code to close the bottom sheet
        }
    }
}
