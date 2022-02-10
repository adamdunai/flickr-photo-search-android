package com.example.flickrphotosearch.main.model

import android.text.Spanned

data class PhotoDetailsUiModel(
    val photoUrl: String,
    val title: String,
    val date: String,
    val viewers: String,
    val username: String,
    val tags: List<String>,
    val description: Spanned,
)
