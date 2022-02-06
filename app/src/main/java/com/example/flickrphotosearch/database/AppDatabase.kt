package com.example.flickrphotosearch.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flickrphotosearch.BuildConfig
import com.example.flickrphotosearch.database.dao.PhotoDao
import com.example.flickrphotosearch.database.dao.RemoteKeyDao
import com.example.flickrphotosearch.database.model.PhotoDataModel
import com.example.flickrphotosearch.database.model.RemoteKeyDataModel

@Database(
    entities = [
        PhotoDataModel::class,
        RemoteKeyDataModel::class
    ],
    version = BuildConfig.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}
