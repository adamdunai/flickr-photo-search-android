package com.example.flickrphotosearch

import android.app.Application
import logcat.AndroidLogcatLogger
import logcat.LogPriority

class FlickPhotoSearchApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidLogcatLogger.installOnDebuggableApp(this, LogPriority.VERBOSE)
    }
}
