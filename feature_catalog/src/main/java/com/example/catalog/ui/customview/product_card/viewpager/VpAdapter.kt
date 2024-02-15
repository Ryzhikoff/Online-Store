package com.example.catalog.ui.customview.product_card.viewpager

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class VpAdapter(
    fragment: Fragment,
    private  val resIds: List<Int>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = resIds.size

    override fun createFragment(position: Int): Fragment =
        Element().apply {
            arguments = bundleOf(Pair(KEY_DRAWABLE_RES_ID, resIds[position]))
        }

    companion object {
        const val KEY_DRAWABLE_RES_ID = "drawable_res_id"
    }
}