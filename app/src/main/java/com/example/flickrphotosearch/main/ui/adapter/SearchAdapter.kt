package com.example.flickrphotosearch.main.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.flickrphotosearch.common.ui.adapter.BindableBaseRecyclerViewAdapter
import com.example.flickrphotosearch.common.ui.view.ViewHolder
import com.example.flickrphotosearch.main.model.SearchItemUiModel
import com.example.flickrphotosearch.main.ui.view.SearchItemView

class SearchAdapter(private val context: Context) :
    BindableBaseRecyclerViewAdapter<SearchItemUiModel, SearchItemView>(SearchDiffUtil) {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): SearchItemView =
        SearchItemView(context)

    override fun onBindViewHolder(holder: ViewHolder<SearchItemView>, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.view.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClicked(photoId: String)
    }
}
