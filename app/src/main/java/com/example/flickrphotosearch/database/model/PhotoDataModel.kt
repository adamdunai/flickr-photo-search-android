package com.example.flickrphotosearch.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Photos")
data class PhotoDataModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val photoId: String,
    val secret: String,
    val server: String,
)
