package com.example.mojalan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mojalan.databinding.Rencana6Binding

class Rencana6Activity : AppCompatActivity() {

    private lateinit var binding: Rencana6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Rencana6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up any necessary logic or event listeners
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        binding.editButton.setOnClickListener {
            // Handle edit button click
        }

        binding.searchButton.setOnClickListener {
            // Handle search button click
        }

        // Add any additional setup logic if necessary
    }
}
