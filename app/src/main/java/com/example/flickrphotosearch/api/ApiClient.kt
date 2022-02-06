package com.example.flickrphotosearch.api

import com.example.flickrphotosearch.api.model.SearchResponseApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("?method=flickr.photos.search")
    suspend fun searchPhotos(
        @Query("text") searchQuery: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int,
    ): SearchResponseApiModel
}
