package com.example.flickrphotosearch.common.ui.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.example.flickrphotosearch.common.ui.view.ViewHolder

/**
 * Base adapter for RecyclerView, simplifies binding on [ViewHolder] items
 */
abstract class BindableBaseRecyclerViewAdapter<T : Any, V>(diffCallback: DiffUtil.ItemCallback<T>) :
    RecyclerViewAdapterBase<T, V>(diffCallback)
        where V : View,
              V : BindableBaseRecyclerViewAdapter.Bindable<T> {

    override fun onBindViewHolder(holder: ViewHolder<V>, position: Int) {
        getItem(position)?.let {
            holder.view.bind(it)
        }
    }

    interface Bindable<T> {
        fun bind(model: T)
    }
}
