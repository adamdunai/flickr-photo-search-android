package com.example.flickrphotosearch.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.flickrphotosearch.R
import com.example.flickrphotosearch.databinding.FragmentPhotoDetailsBinding
import com.example.flickrphotosearch.main.model.PhotoDetailsUiModel
import com.example.flickrphotosearch.main.model.PhotoDetailsUiState
import com.example.flickrphotosearch.main.ui.viewmodel.PhotoDetailsViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoDetailsFragment : BaseFragment() {

    private var _binding: FragmentPhotoDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PhotoDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(R.string.photo_details_title)

        lifecycleScope.launch {
            viewModel.uiState.collectLatest { photoDetailsState ->
                when (photoDetailsState) {
                    PhotoDetailsUiState.Empty -> showComponents(
                        progressBarVisible = false,
                        errorVisible = false,
                        contentVisible = false
                    )
                    PhotoDetailsUiState.Error -> showComponents(
                        progressBarVisible = false,
                        errorVisible = true,
                        contentVisible = false
                    )
                    PhotoDetailsUiState.Loading -> showComponents(
                        progressBarVisible = true,
                        errorVisible = false,
                        contentVisible = false
                    )
                    is PhotoDetailsUiState.Success -> {
                        showComponents(
                            progressBarVisible = false,
                            errorVisible = false,
                            contentVisible = true
                        )
                        initUiFromModel(photoDetailsState.data)
                    }
                }
            }
        }

        binding.retryButton.setOnClickListener {
            viewModel.getPhotoDetails()
        }
    }

    private fun showComponents(
        progressBarVisible: Boolean,
        errorVisible: Boolean,
        contentVisible: Boolean,
    ) {
        with(binding) {
            progressBar.isVisible = progressBarVisible
            errorLinearLayout.isVisible = errorVisible
            contentConstraintLayout.isVisible = contentVisible
        }
    }

    private fun initUiFromModel(model: PhotoDetailsUiModel) {
        with(binding) {
            titleTextView.text = model.title
            dateTextView.text = model.date
            viewersTextView.text = model.viewers
            usernameTextView.text = model.username
            descriptionTextView.text = model.description
            photoView.load(model.photoUrl) {
                crossfade(true)
            }

            fillTagChips(model.tags)
        }
    }

    private fun fillTagChips(tags: List<String>) {
        tags.forEach { tag ->
            binding.tagChipGroup.addView(
                Chip(getMainActivity()).apply {
                    text = tag
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
