package com.tangosource.android.daggerdemo.ui.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Adds blank space between RecyclerView items
 */
class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        when {
            // if last item
            parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.dec() -> {
                outRect.apply {
                    left = space
                    right = space
                }
            }
            // first item
            parent.getChildAdapterPosition(view) == 0 -> {
                outRect.apply {
                    left = space
                    right = space
                    bottom = space
                }
            }
            else -> {
                outRect.apply {
                    left = space
                    right = space
                    bottom = space
                }
            }
        }
    }
}