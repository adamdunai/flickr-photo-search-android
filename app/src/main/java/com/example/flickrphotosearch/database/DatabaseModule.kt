package com.example.flickrphotosearch.database

import android.content.Context
import androidx.room.Room
import com.example.flickrphotosearch.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            BuildConfig.DATABASE_NAME
        )
            .build()

    @Singleton
    @Provides
    fun providePhotoDao(database: AppDatabase) = database.photoDao()

    @Singleton
    @Provides
    fun providesRemoteKeyDao(database: AppDatabase) = database.remoteKeyDao()
}
