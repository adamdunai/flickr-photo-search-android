package com.example.flickrphotosearch.main.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.flickrphotosearch.main.model.SearchItemUiModel

object SearchDiffUtil : DiffUtil.ItemCallback<SearchItemUiModel>() {

    override fun areItemsTheSame(oldItem: SearchItemUiModel, newItem: SearchItemUiModel): Boolean =
        oldItem.hashCode() == newItem.hashCode()

    override fun areContentsTheSame(
        oldItem: SearchItemUiModel,
        newItem: SearchItemUiModel,
    ): Boolean =
        oldItem == newItem
}
