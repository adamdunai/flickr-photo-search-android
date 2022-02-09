package com.example.flickrphotosearch.main.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.flickrphotosearch.common.mapper.toUiModel
import com.example.flickrphotosearch.data.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class PhotoSearchViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PhotoRepository,
) : ViewModel() {

    companion object {
        const val KEY_QUERY = "query"
        const val DEFAULT_QUERY = "dog"
    }

    init {
        if (!savedStateHandle.contains(KEY_QUERY)) {
            savedStateHandle.set(KEY_QUERY, DEFAULT_QUERY)
        }
    }

    @ExperimentalCoroutinesApi
    val photosDataFlow = savedStateHandle.getLiveData<String>(KEY_QUERY)
        .asFlow()
        .flatMapLatest {
            repository.getSearchResultStream(it).map { pagingData ->
                pagingData.map { dataModel ->
                    dataModel.toUiModel()
                }
            }
        }
        .cachedIn(viewModelScope)

    fun searchPhoto(query: String) {
        if (savedStateHandle.get<String>(KEY_QUERY) != query) {
            savedStateHandle.set(KEY_QUERY, query)
        }
    }
}
