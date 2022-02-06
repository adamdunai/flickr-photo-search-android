package com.example.flickrphotosearch.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Photos")
data class PhotoDataModel(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val photoId: String,
    val secret: String,
    val server: String,
)
