package com.example.flickrphotosearch.main.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrphotosearch.common.mapper.toUiModel
import com.example.flickrphotosearch.data.PhotoRepository
import com.example.flickrphotosearch.main.model.PhotoDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PhotoRepository,
) : ViewModel() {

    companion object {
        private const val KEY_PHOTO_ID = "photoId"
    }

    init {
        getPhotoDetails()
    }

    private val _uiState = MutableStateFlow<PhotoDetailsUiState>(PhotoDetailsUiState.Empty)
    val uiState: StateFlow<PhotoDetailsUiState> = _uiState

    fun getPhotoDetails() {
        savedStateHandle.get<String>(KEY_PHOTO_ID)?.let { photoId ->
            viewModelScope.launch(Dispatchers.IO) {
                _uiState.value = PhotoDetailsUiState.Loading
                try {
                    val response = repository.getPhotoDetails(photoId)
                    _uiState.value = PhotoDetailsUiState.Success(response.toUiModel())
                } catch (exception: Exception) {
                    _uiState.value = PhotoDetailsUiState.Error
                }
            }
        }
    }
}
