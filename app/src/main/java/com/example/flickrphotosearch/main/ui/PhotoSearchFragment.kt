package com.example.flickrphotosearch.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flickrphotosearch.R
import com.example.flickrphotosearch.common.extension.setOnActionDone
import com.example.flickrphotosearch.databinding.FragmentPhotoSearchBinding
import com.example.flickrphotosearch.main.navigation.MainNavigator
import com.example.flickrphotosearch.main.ui.adapter.SearchAdapter
import com.example.flickrphotosearch.main.ui.viewmodel.PhotoSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
@AndroidEntryPoint
class PhotoSearchFragment : BaseFragment() {

    private lateinit var searchAdapter: SearchAdapter

    private var _binding: FragmentPhotoSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PhotoSearchViewModel by viewModels()

    @Inject
    lateinit var navigator: MainNavigator

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

        searchAdapter = SearchAdapter(getMainActivity()).apply {
            onItemClickListener = object : SearchAdapter.OnItemClickListener {
                override fun onItemClicked(photoId: String) {
                    navigator.navigateToDetails(photoId)
                }
            }
        }

        setTitle(R.string.photo_search_title)

        with(binding.searchTextInputEditText) {
            this.setOnActionDone {
                viewModel.searchPhoto(this.text?.trim().toString())
            }
        }

        binding.retryButton.setOnClickListener {
            searchAdapter.retry()
        }

        with(binding.searchRecyclerView) {
            layoutManager = GridLayoutManager(getMainActivity(), 2)
            adapter = searchAdapter
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.photosDataFlow.collectLatest(searchAdapter::submitData)
            }
        }

        // TODO refactor
        lifecycleScope.launch {
            searchAdapter.loadStateFlow.collect { loadState ->
                with(binding) {
                    noResultTextView.isVisible =
                        loadState.refresh is LoadState.NotLoading && searchAdapter.itemCount == 0
                    searchRecyclerView.isVisible =
                        loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading
                    progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading
                    retryButton.isVisible =
                        loadState.mediator?.refresh is LoadState.Error && searchAdapter.itemCount == 0
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
