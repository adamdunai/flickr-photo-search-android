package com.example.flickrphotosearch.common.extension

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.setOnActionDone(callOnActionDone: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            callOnActionDone()
        }
        false
    }
}
