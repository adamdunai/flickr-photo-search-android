package com.example.flickrphotosearch.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flickrphotosearch.database.model.RemoteKeyDataModel

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeyList: List<RemoteKeyDataModel>)

    @Query(
        "SELECT *" +
            " FROM RemoteKeys" +
            " WHERE photoId = :photoId"
    )
    suspend fun getRemoteKeyByPhotoId(photoId: String): RemoteKeyDataModel?

    @Query("DELETE FROM RemoteKeys")
    suspend fun deleteRemoteKeys()
}
