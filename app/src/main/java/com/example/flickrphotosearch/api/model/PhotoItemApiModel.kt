package com.example.flickrphotosearch.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoItemApiModel(
    @Json(name = "id")
    val photoId: String,
    val secret: String,
    val server: String,
)
