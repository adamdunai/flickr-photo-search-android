package com.example.flickrphotosearch.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flickrphotosearch.databinding.FragmentPhotoDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailsFragment : BaseFragment() {

    private var _binding: FragmentPhotoDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
