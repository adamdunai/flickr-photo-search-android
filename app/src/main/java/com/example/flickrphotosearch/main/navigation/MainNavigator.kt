package com.example.flickrphotosearch.main.navigation

import android.content.Context
import com.example.flickrphotosearch.common.navigation.BaseNavigator
import com.example.flickrphotosearch.main.MainActivity
import com.example.flickrphotosearch.main.ui.PhotoSearchFragmentDirections
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MainNavigator @Inject constructor(@ActivityContext context: Context) :
    BaseNavigator(context as MainActivity) {

    fun navigateToDetails(photoId: String) {
        navigate(
            PhotoSearchFragmentDirections.actionPhotoSearchFragmentToPhotoDetailFragment(photoId)
        )
    }
}
