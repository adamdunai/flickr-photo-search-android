package com.example.flickrphotosearch.main.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import coil.load
import com.example.flickrphotosearch.R
import com.example.flickrphotosearch.common.ui.adapter.BindableBaseRecyclerViewAdapter
import com.example.flickrphotosearch.databinding.ViewSearchItemBinding
import com.example.flickrphotosearch.main.model.SearchItemUiModel

class SearchItemView :
    FrameLayout,
    BindableBaseRecyclerViewAdapter.Bindable<SearchItemUiModel> {

    private val binding = ViewSearchItemBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
        super(context, attrs, defStyleAttr)

    init {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )

        val padding = resources.getDimension(R.dimen.margin_padding_size_small).toInt()
        setPadding(padding, padding, padding, padding)
    }

    override fun bind(model: SearchItemUiModel) {
        binding.itemImageView.load(model.photoUrl) {
            crossfade(true)
        }
    }
}
