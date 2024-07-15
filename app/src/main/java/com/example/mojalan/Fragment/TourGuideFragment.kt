package com.example.mojalan.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mojalan.R
import com.example.mojalan.TourGuide
import com.example.mojalan.TourGuideAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TourGuideFragment : Fragment(), TourGuideAdapter.OnTourGuideClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TourGuideAdapter
    private lateinit var database: DatabaseReference
    private lateinit var tourGuideList: MutableList<TourGuide>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tour_guide, container, false)

        recyclerView = view.findViewById(R.id.recycler_view_tour_guide)
        recyclerView.layoutManager = LinearLayoutManager(context)
        tourGuideList = mutableListOf()
        adapter = TourGuideAdapter(tourGuideList, this)
        recyclerView.adapter = adapter

        database = FirebaseDatabase.getInstance().reference.child("tourGuides")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tourGuideList.clear()
                for (dataSnapshot in snapshot.children) {
                    val tourGuide = dataSnapshot.getValue(TourGuide::class.java)
                    if (tourGuide != null) {
                        tourGuideList.add(tourGuide)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors.
            }
        })

        return view
    }

    override fun onTourGuideClick(tourGuide: TourGuide) {
        val detailFragment = DetailTourGuideFragment.newInstance(tourGuide)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}
