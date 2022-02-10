package com.example.flickrphotosearch.main.model

sealed class PhotoDetailsUiState {
    object Empty : PhotoDetailsUiState()
    object Loading : PhotoDetailsUiState()
    data class Success(val data: PhotoDetailsUiModel) : PhotoDetailsUiState()
    object Error : PhotoDetailsUiState()
}
