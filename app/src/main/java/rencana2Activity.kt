package com.example.mojalan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class rencana2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rencana2)

        // Find the button by its ID
        val buatButton: Button = findViewById(R.id.login_button)

        // Set an OnClickListener to the button
        buatButton.setOnClickListener {
            // Create an Intent to start Rencana3Activity
            val intent = Intent(this, Rencana3Activity::class.java)
            // Start the new activity
            startActivity(intent)
        }
    }
}
