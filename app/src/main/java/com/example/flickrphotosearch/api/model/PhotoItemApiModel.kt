package com.example.flickrphotosearch.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoItemApiModel(
    val id: String,
    val secret: String,
    val server: String,
)
