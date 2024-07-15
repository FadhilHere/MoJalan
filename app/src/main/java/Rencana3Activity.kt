package com.example.mojalan

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

class Rencana3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rencana3)

        // Initialize views
        val logoImageView: ImageView = findViewById(R.id.logoImageView)
        val shadowView: View = findViewById(R.id.shadowView)
        val textViewTitle: TextView = findViewById(R.id.textViewTitle)
        val addButton: ImageView = findViewById(R.id.addButton)
        val tripIcon: ImageView = findViewById(R.id.tripIcon)
        val tripTitle: TextView = findViewById(R.id.tripTitle)
        val cardView: CardView = findViewById(R.id.tripCardView) // Updated to tripCardView

        // Set up event listeners, if needed
        addButton.setOnClickListener {
            // Handle add button click
        }
    }
}
