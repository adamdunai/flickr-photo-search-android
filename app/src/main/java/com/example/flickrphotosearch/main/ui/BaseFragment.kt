package com.example.flickrphotosearch.main.ui

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.flickrphotosearch.main.MainActivity

abstract class BaseFragment : Fragment() {

    protected fun setTitle(@StringRes titleResId: Int) {
        getMainActivity().setTitle(titleResId)
    }

    protected fun getMainActivity() = activity as MainActivity
}
