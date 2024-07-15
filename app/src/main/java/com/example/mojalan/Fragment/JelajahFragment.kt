package com.example.mojalan.Fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.mojalan.R

class JelajahFragment : Fragment() {

    private lateinit var tabTourGuide: TextView
    private lateinit var tabPaket: TextView
    private lateinit var indicator: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_jelajah, container, false)

        tabTourGuide = view.findViewById(R.id.tab_tour_guide)
        tabPaket = view.findViewById(R.id.tab_paket)
        indicator = view.findViewById(R.id.indicator)

        tabTourGuide.setOnClickListener {
            showFragment(TourGuideFragment())
            animateIndicator(0)
        }

        tabPaket.setOnClickListener {
            showFragment(PaketFragment())
            animateIndicator(1)
        }

        // Default fragment
        showFragment(TourGuideFragment())
        return view
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }

    private fun animateIndicator(position: Int) {
        val translationX = if (position == 0) 0f else tabTourGuide.width.toFloat()
        ObjectAnimator.ofFloat(indicator, "translationX", translationX).apply {
            duration = 300 // animation duration in milliseconds
            interpolator = FastOutSlowInInterpolator()
            start()
        }
    }
}
