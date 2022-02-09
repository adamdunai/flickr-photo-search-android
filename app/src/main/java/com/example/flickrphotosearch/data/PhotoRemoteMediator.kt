package com.example.flickrphotosearch.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.flickrphotosearch.api.ApiClient
import com.example.flickrphotosearch.common.mapper.toDataModelList
import com.example.flickrphotosearch.database.AppDatabase
import com.example.flickrphotosearch.database.model.PhotoDataModel
import com.example.flickrphotosearch.database.model.RemoteKeyDataModel
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class PhotoRemoteMediator(
    private val query: String,
    private val apiClient: ApiClient,
    private val appDatabase: AppDatabase,
) : RemoteMediator<Int, PhotoDataModel>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoDataModel>,
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                remoteKey?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKey = getRemoteKeyForFirstItem(state)
                remoteKey?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
            }
            LoadType.APPEND -> {
                val remoteKey = getRemoteKeyForLastItem(state)
                remoteKey?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
            }
        }

        try {
            val response = apiClient.searchPhotos(
                searchQuery = query,
                page = page,
                itemsPerPage = state.config.pageSize
            )

            val photoList = response.photos.photoList
            val endOfPaginationReached = photoList.isEmpty()
            appDatabase.withTransaction {
                if (LoadType.REFRESH == loadType) {
                    appDatabase.clearAllTables()
                }

                val previousKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeyList = photoList.map {
                    RemoteKeyDataModel(
                        photoId = it.photoId,
                        prevKey = previousKey,
                        nextKey = nextKey
                    )
                }
                with(appDatabase) {
                    remoteKeyDao().insertAll(remoteKeyList)
                    photoDao().insertAll(photoList.toDataModelList())
                }
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PhotoDataModel>,
    ): RemoteKeyDataModel? =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.photoId?.let { photoId ->
                appDatabase.remoteKeyDao().getRemoteKeyByPhotoId(photoId)
            }
        }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PhotoDataModel>,
    ): RemoteKeyDataModel? =
        state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { photoDataModel ->
                appDatabase.remoteKeyDao().getRemoteKeyByPhotoId(photoDataModel.photoId)
            }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PhotoDataModel>,
    ): RemoteKeyDataModel? =
        state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { photoDataModel ->
                appDatabase.remoteKeyDao().getRemoteKeyByPhotoId(photoDataModel.photoId)
            }
}
