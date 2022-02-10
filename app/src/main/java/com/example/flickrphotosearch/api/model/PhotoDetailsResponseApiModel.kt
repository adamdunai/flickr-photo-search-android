package com.example.flickrphotosearch.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoDetailsResponseApiModel(
    val id: String,
    val secret: String,
    val server: String,
    @Json(name = "dateuploaded")
    val dateUploadedInSeconds: String,
    val owner: OwnerDetailsResponseApiModel,
    val title: ContentDetailsResponseApiModel,
    val description: ContentDetailsResponseApiModel,
    val views: String,
    val tags: TagDetailsResponseApiModel,
)
