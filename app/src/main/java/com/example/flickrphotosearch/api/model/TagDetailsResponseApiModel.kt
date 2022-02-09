package com.example.flickrphotosearch.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TagDetailsResponseApiModel(
    val tag: List<TagItemDetailsResponseApiModel>,
)
