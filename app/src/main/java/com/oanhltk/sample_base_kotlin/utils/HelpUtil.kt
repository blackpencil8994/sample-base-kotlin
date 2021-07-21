package com.oanhltk.sample_base_kotlin.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.oanhltk.sample_base_kotlin.ui.main.listener.OnSnapPositionChangeListener
import com.oanhltk.sample_base_kotlin.ui.main.listener.SnapOnScrollListener

fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
    val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}

fun RecyclerView.attachSnapHelperWithListener(
        snapHelper: SnapHelper,
        onSnapPositionChangeListener: OnSnapPositionChangeListener,
        behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL) {
    snapHelper.attachToRecyclerView(this)
    val snapOnScrollListener = SnapOnScrollListener(snapHelper, behavior, onSnapPositionChangeListener )
    addOnScrollListener(snapOnScrollListener)
}