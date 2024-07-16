package com.example.mojalan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mojalan.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up any necessary logic or event listeners
        binding.editButton.setOnClickListener {
            // Handle edit button click
        }

        // Add event listeners for menu items
        binding.menuPesanan.setOnClickListener {
            // Handle "Pesanan" menu item click
            val intent = Intent(this, PesananUserActivity::class.java)
            startActivity(intent)
        }

        binding.iconPesanan.setOnClickListener {
            // Handle "Pesanan" icon click
            val intent = Intent(this, PesananUserActivity::class.java)
            startActivity(intent)
        }

        binding.menuRencanaPerjalanan.setOnClickListener {
            // Handle "Rencana Perjalanan Ku" menu item click
        }

        binding.menuNotifikasi.setOnClickListener {
            // Handle "Notifikasi" menu item click
        }

        binding.menuPromo.setOnClickListener {
            // Handle "Promo" menu item click
        }

        binding.menuPenilaian.setOnClickListener {
            // Handle "Penilaian" menu item click
        }

        binding.menuBantuan.setOnClickListener {
            // Handle "Bantuan" menu item click
        }

        binding.menuNilaiKami.setOnClickListener {
            // Handle "Nilai Kami" menu item click
        }

        binding.menuPengaturan.setOnClickListener {
            // Handle "Pengaturan" menu item click
        }

        binding.logoutButton.setOnClickListener {
            // Handle logout button click
        }
    }
}
