package com.example.mojalan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mojalan.Fragment.DashboardFragment
import com.example.mojalan.Fragment.ProfileFragment
import com.example.mojalan.Fragment.TambahDataFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class TourGuideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour_guide)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.navigation_dashboard -> selectedFragment = DashboardFragment()
                R.id.navigation_add_data -> selectedFragment = TambahDataFragment()
                R.id.navigation_profile -> selectedFragment = ProfileFragment()
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit()
            }
            true
        }

        // Set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, DashboardFragment()).commit()
        }
    }
}
