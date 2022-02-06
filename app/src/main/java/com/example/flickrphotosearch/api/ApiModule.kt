package com.example.flickrphotosearch.api

import com.example.flickrphotosearch.BuildConfig
import com.example.flickrphotosearch.api.interceptor.DefaultQueryParametersInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiClient =
        retrofit.create(ApiClient::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        defaultQueryParametersInterceptor: DefaultQueryParametersInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(defaultQueryParametersInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
}
