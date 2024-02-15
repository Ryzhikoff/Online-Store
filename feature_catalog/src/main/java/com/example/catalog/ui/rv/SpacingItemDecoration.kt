package com.example.catalog.ui.rv

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration (
    private val paddingTopInDp: Int = 0,
    private val paddingStartInDp: Int = 0,
    private val paddingEndInDp: Int = 0,
    private val paddingBottomInDp: Int = 0
): RecyclerView.ItemDecoration() {
    private val Int.convertPx: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = paddingTopInDp.convertPx
        outRect.left = paddingStartInDp.convertPx
        outRect.right = paddingEndInDp.convertPx
        outRect.bottom = paddingBottomInDp.convertPx
    }
}