package com.example.flickrphotosearch.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TagItemDetailsResponseApiModel(
    @Json(name = "raw")
    val tag: String,
)
