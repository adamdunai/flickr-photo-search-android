package com.example.flickrphotosearch.common.mapper

import com.example.flickrphotosearch.BuildConfig
import com.example.flickrphotosearch.database.model.PhotoDataModel
import com.example.flickrphotosearch.main.model.SearchItemUiModel

fun PhotoDataModel.toUiModel() =
    SearchItemUiModel(
        photoId = photoId,
        photoUrl = "${BuildConfig.BASE_PHOTO_URL}$server/${photoId}_$secret.jpg"
    )
