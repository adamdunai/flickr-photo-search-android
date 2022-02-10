package com.example.flickrphotosearch.common.extension

import android.view.inputmethod.EditorInfo
import android.widget.EditText

/**
 * Syntactic sugar to add search IME action.
 */
fun EditText.setOnActionSearch(callOnActionSearch: (String) -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (EditorInfo.IME_ACTION_SEARCH == actionId) {
            callOnActionSearch(text.toString())
        }
        false
    }
}
