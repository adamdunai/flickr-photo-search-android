package com.example.flickrphotosearch.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.flickrphotosearch.api.ApiClient
import com.example.flickrphotosearch.database.AppDatabase
import com.example.flickrphotosearch.database.model.PhotoDataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class PhotoRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val appDatabase: AppDatabase,
) {

    companion object {
        /** [PagingConfig.pageSize] and [PagingConfig.initialLoadSize] are used to inform
         * [PagingSource.LoadParams.loadSize] but is not enforced. A [PagingSource] may completely
         * ignore this value.
         */
        private const val DEFAULT_PAGE_SIZE = 20
    }

    fun getSearchResultStream(query: String): Flow<PagingData<PhotoDataModel>> {
        val pagingSourceFactory = { appDatabase.photoDao().getPhotoPagingSource() }

        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                initialLoadSize = DEFAULT_PAGE_SIZE,
                prefetchDistance = DEFAULT_PAGE_SIZE * 2,
                enablePlaceholders = false
            ),
            remoteMediator = PhotoRemoteMediator(
                query = query,
                apiClient = apiClient,
                appDatabase = appDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}
