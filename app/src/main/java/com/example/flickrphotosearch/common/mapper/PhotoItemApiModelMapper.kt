package com.example.flickrphotosearch.common.mapper

import com.example.flickrphotosearch.api.model.PhotoItemApiModel
import com.example.flickrphotosearch.database.model.PhotoDataModel

fun PhotoItemApiModel.toDataModel() =
    PhotoDataModel(
        photoId = photoId,
        secret = secret,
        server = server
    )

fun List<PhotoItemApiModel>.toDataModelList() =
    map {
        it.toDataModel()
    }
