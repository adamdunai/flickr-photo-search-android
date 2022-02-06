package com.example.flickrphotosearch.main.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.flickrphotosearch.common.ui.adapter.BindableBaseRecyclerViewAdapter
import com.example.flickrphotosearch.main.model.SearchItemUiModel
import com.example.flickrphotosearch.main.ui.view.SearchItemView

class SearchAdapter(private val context: Context) :
    BindableBaseRecyclerViewAdapter<SearchItemUiModel, SearchItemView>(SearchDiffUtil) {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): SearchItemView =
        SearchItemView(context)
}
