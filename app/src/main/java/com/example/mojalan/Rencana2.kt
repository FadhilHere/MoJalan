package com.example.mojalan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Rencana2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rencana2) // Sesuaikan dengan layout activity yang benar

        // Menggunakan findViewById pada view yang dihasilkan
        val button = findViewById<Button>(R.id.login_button)

        // Menetapkan reaksi saat button diklik
        button.setOnClickListener {
            val next = Intent(this, Rencana3::class.java)
            startActivity(next)
        }
    }
}
