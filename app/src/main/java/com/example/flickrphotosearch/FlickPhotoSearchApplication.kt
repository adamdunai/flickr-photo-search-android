package com.example.flickrphotosearch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import logcat.AndroidLogcatLogger
import logcat.LogPriority

@HiltAndroidApp
class FlickPhotoSearchApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidLogcatLogger.installOnDebuggableApp(this, LogPriority.VERBOSE)
    }
}
