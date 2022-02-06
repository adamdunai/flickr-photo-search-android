package com.example.flickrphotosearch.api.interceptor

import com.example.flickrphotosearch.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DefaultQueryParametersInterceptor @Inject constructor() : Interceptor {

    companion object {
        const val QUERY_PARAMETER_FORMAT_KEY = "format"
        const val QUERY_PARAMETER_FORMAT_VALUE = "json"
        const val QUERY_PARAMETER_NOJSONCALLBACK_KEY = "nojsoncallback"
        const val QUERY_PARAMETER_NOJSONCALLBACK_VALUE = "1"
        const val QUERY_PARAMETER_API_KEY_VALUE = "api_key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(
                QUERY_PARAMETER_FORMAT_KEY,
                QUERY_PARAMETER_FORMAT_VALUE
            )
            .addQueryParameter(
                QUERY_PARAMETER_NOJSONCALLBACK_KEY,
                QUERY_PARAMETER_NOJSONCALLBACK_VALUE
            )
            .addQueryParameter(
                QUERY_PARAMETER_API_KEY_VALUE,
                BuildConfig.FLICKR_API_KEY
            )
            .build()

        request = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}
