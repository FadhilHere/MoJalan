package com.example.mojalan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mojalan.databinding.Rencana5Binding

class Rencana5Activity : AppCompatActivity() {
    private lateinit var binding: Rencana5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Rencana5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            // Code to add a new trip
        }

        binding.closeButton.setOnClickListener {
            // Code to close the bottom sheet
        }
    }
}
