package com.example.catalog.ui.rv

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.catalog.models.ProductUi
import com.example.catalog.ui.customview.product_card.ProductCardView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class ProductDelegateAdapter(
    private val fragment: Fragment
) : AbsListItemAdapterDelegate<ProductUi, ProductAdapterItem, ProductDelegateAdapter.ViewHolder>() {


    override fun isForViewType(item: ProductAdapterItem, items: MutableList<ProductAdapterItem>, position: Int): Boolean =
        item is ProductUi

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemProduct = ProductCardView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return ViewHolder(itemProduct)
    }

    override fun onBindViewHolder(item: ProductUi, holder: ViewHolder, payloads: MutableList<Any>) {
        holder.init(item)
    }

    inner class ViewHolder(private val itemProduct: ProductCardView) : RecyclerView.ViewHolder(itemProduct) {
        fun init(productUi: ProductUi) {
            itemProduct.init(fragment, productUi)
        }
    }
}