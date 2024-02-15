package com.example.catalog.ui.rv

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class ProductAdapter(private val fragment: Fragment) : ListDelegationAdapter<List<ProductAdapterItem>>() {

    init {
        delegatesManager.addDelegate(ProductDelegateAdapter(fragment))
    }

    fun submitList(newList: List<ProductAdapterItem>) {
        val diff = ProductDiff(items.orEmpty(), newList)
        val diffResult = DiffUtil.calculateDiff(diff)
        setItems(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    class ProductDiff(
        private val oldList: List<ProductAdapterItem>,
        private val newList: List<ProductAdapterItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}