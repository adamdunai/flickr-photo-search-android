package com.example.flickrphotosearch.common.navigation

import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.flickrphotosearch.R
import com.example.flickrphotosearch.main.MainActivity

abstract class BaseNavigator(mainActivity: MainActivity) {

    private val navController by lazy {
        Navigation.findNavController(mainActivity, R.id.fragmentNavHost)
    }

    protected fun navigate(direction: NavDirections) {
        navController.navigate(direction)
    }
}
