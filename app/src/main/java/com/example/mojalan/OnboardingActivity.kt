package com.example.mojalan

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.mojalan.R

class OnboardingActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var onboardingAdapter: OnboardingAdapter
    private var currentPage = 0
    private val handler = Handler()
    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            if (currentPage == onboardingAdapter.itemCount - 1) {
                // Stop auto scrolling when reaching the last page
                handler.removeCallbacks(this)
                return
            }
            currentPage++
            viewPager.setCurrentItem(currentPage, true)
            handler.postDelayed(this, 2000) // 5000 milliseconds = 5 seconds
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val layouts = listOf(
            R.layout.activity_landing1,
            R.layout.activity_landing2,
            R.layout.activity_landing3,
            R.layout.activity_landing4
        )

        viewPager = findViewById(R.id.viewPager)
        onboardingAdapter = OnboardingAdapter(this, layouts)
        viewPager.adapter = onboardingAdapter

        // Start auto scrolling
        handler.postDelayed(autoScrollRunnable, 2000) // Start scrolling after 5 seconds
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove callbacks to prevent memory leaks
        handler.removeCallbacks(autoScrollRunnable)
    }
}


