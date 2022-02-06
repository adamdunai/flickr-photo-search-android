package com.example.flickrphotosearch.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flickrphotosearch.database.model.PhotoDataModel

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photoList: List<PhotoDataModel>)

    @Query(
        "SELECT *" +
            " FROM Photos" +
            " ORDER BY id ASC"
    )
    fun getPhotoPagingSource(): PagingSource<Int, PhotoDataModel>

    @Query("DELETE FROM Photos")
    suspend fun deletePhotos()
}
