package com.oanhltk.sample_base_kotlin.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        Glide.with(view.context).load(url).into(view)
    }
}

@BindingAdapter("visibleOrGone")
fun setVisibleOrGone(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}
