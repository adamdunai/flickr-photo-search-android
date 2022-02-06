package com.example.flickrphotosearch.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.flickrphotosearch.R
import com.example.flickrphotosearch.common.extension.setOnActionDone
import com.example.flickrphotosearch.databinding.FragmentPhotoSearchBinding
import com.example.flickrphotosearch.main.ui.adapter.SearchAdapter

class PhotoSearchFragment : BaseFragment() {

    private lateinit var searchAdapter: SearchAdapter

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

        searchAdapter = SearchAdapter(getMainActivity())

        setTitle(R.string.photo_search_title)

        binding.searchTextInputEditText.setOnActionDone {
            // TODO search for images
        }

        with(binding.searchRecyclerView) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = searchAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
