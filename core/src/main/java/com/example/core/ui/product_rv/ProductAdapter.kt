package com.example.core.ui.product_rv

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.example.core.interfaces.ProductAdapterItem
import com.example.core.models.ProductUi
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class ProductAdapter(
    fragment: Fragment,
    onFavoriteClickListener: OnFavoriteClickListener,
    onItemClickListener: OnItemClickListener
) : ListDelegationAdapter<List<ProductAdapterItem>>() {

    init {
        delegatesManager.addDelegate(
            ProductDelegateAdapter(
                fragment,
                onFavoriteClickListener,
                onItemClickListener
            )
        )
    }

    fun submitList(newList: List<ProductAdapterItem>) {
        println("before ${items?.size} ")
        val diff = ProductDiff(items.orEmpty(), newList)
        val diffResult = DiffUtil.calculateDiff(diff)
        setItems(newList)
        println("after ${items?.size}")
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

    interface OnItemClickListener{
        fun onClick(productUi: ProductUi)
    }

    interface OnFavoriteClickListener {
        fun onClick(productUi: ProductUi)
    }
}