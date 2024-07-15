package com.example.mojalan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mojalan.databinding.PesananUserBinding

class PesananUserActivity : AppCompatActivity() {

    private lateinit var binding: PesananUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PesananUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up back button click listener
        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }

        // Add additional setup logic if necessary
    }
}
