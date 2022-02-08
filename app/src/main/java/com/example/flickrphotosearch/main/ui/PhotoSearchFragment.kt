package com.example.flickrphotosearch.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flickrphotosearch.R
import com.example.flickrphotosearch.common.extension.setOnActionDone
import com.example.flickrphotosearch.databinding.FragmentPhotoSearchBinding
import com.example.flickrphotosearch.main.ui.adapter.SearchAdapter
import com.example.flickrphotosearch.main.ui.viewmodel.PhotoSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
@AndroidEntryPoint
class PhotoSearchFragment : BaseFragment() {

    private lateinit var searchAdapter: SearchAdapter

    private var _binding: FragmentPhotoSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PhotoSearchViewModel by viewModels()

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

        with(binding.searchTextInputEditText) {
            this.setOnActionDone {
                viewModel.searchPhoto(this.text?.trim().toString())
            }
        }

        with(binding.searchRecyclerView) {
            layoutManager = GridLayoutManager(getMainActivity(), 2)
            adapter = searchAdapter
        }

        lifecycleScope.launch {
            viewModel.photosDataFlow.collectLatest(searchAdapter::submitData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
