package com.example.mojalan.Fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mojalan.R
import com.google.android.material.tabs.TabLayout

class ListTravelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_travel)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val tab = tabLayout.getTabAt(1) // Index 1 untuk "Paket"
        tab?.select()
    }
}
