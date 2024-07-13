package com.example.mojalan.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mojalan.TourGuide
import com.example.mojalan.databinding.FragmentDetailTourGuideBinding

class DetailTourGuideFragment : Fragment() {

    private var tourGuide: TourGuide? = null
    private var _binding: FragmentDetailTourGuideBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tourGuide = it.getParcelable(ARG_TOUR_GUIDE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTourGuideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tourGuide?.let {
            binding.detailTourGuideImage.setImageResource(it.imageResId)
            binding.detailTourGuideName.text = it.name
            binding.detailTourGuideRating.text = it.rating
            binding.detailTourGuideTag1.text = it.tag1
            binding.detailTourGuideTag2.text = it.tag2
            binding.detailTourGuideLocation.text = it.location
            binding.detailTourGuideDescription.text = it.description
            binding.detailTourGuideLanguages.text = it.languages
            binding.detailTourGuidePrice.text = it.price
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TOUR_GUIDE = "tour_guide"

        @JvmStatic
        fun newInstance(tourGuide: TourGuide) =
            DetailTourGuideFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_TOUR_GUIDE, tourGuide)
                }
            }
    }
}
