package com.example.flickrphotosearch.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RemoteKeys")
data class RemoteKeyDataModel(
    @PrimaryKey val photoId: String,
    val prevKey: Int?,
    val nextKey: Int?,
)
