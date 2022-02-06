package com.example.flickrphotosearch.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosResponseApiModel(
    val page: Int,
    val pages: Int,
    val photo: List<PhotoItemApiModel>,
)
