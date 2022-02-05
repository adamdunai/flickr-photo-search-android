package com.example.flickrphotosearch.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flickrphotosearch.R
import com.example.flickrphotosearch.common.extension.setOnActionDone
import com.example.flickrphotosearch.databinding.FragmentPhotoSearchBinding

class PhotoSearchFragment : BaseFragment() {

    private var _binding: FragmentPhotoSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPhotoSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(R.string.photo_search_title)

        binding.searchTextInputEditText.setOnActionDone {
            // TODO search for images
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
