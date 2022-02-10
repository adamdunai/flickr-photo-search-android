package com.example.flickrphotosearch.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OwnerDetailsResponseApiModel(
    val username: String,
)
