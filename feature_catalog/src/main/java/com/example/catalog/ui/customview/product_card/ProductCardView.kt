package com.example.catalog.ui.customview.product_card

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.catalog.R
import com.example.catalog.databinding.ProductCardBinding
import com.example.catalog.models.ProductUi
import com.example.catalog.ui.customview.product_card.viewpager.VpAdapter

class ProductCardView (context: Context) : ConstraintLayout(context) {

    private val binding: ProductCardBinding

    init {
        binding = ProductCardBinding.bind(inflate(context, R.layout.product_card, this))
    }

    constructor(
        fragment: Fragment,
        productUi: ProductUi
    ): this(fragment.requireContext()) {
        init(fragment, productUi)
    }

    fun init(fragment: Fragment, productUi: ProductUi) {
        setData(productUi)
        initViewpager(fragment, productUi)
    }

    private fun setData(productUi: ProductUi) = with(binding) {
        val oldPriceText = "${productUi.oldPrice} ${productUi.unit}"
        oldPrice.text = oldPriceText

        newPrice.text = productUi.newPrice.toString()
        val discountText = "-${productUi.discount}%"
        discount.text = discountText
        name.text = productUi.title
        subtitle.text = productUi.subtitle



        productUi.rating?.let {
            rating.text = productUi.rating.toString()
            val feedbackCountsText = "(${productUi.feedbackCounts})"
            feedbackCount.text = feedbackCountsText
            ratingContainer.visibility = View.VISIBLE
        }



    }

    private fun initViewpager(fragment: Fragment, productUi: ProductUi) {

        val adapter = VpAdapter(fragment, productUi.drawableResIds)
        binding.viewPager.adapter = adapter

        binding.indicator.setViewPager(binding.viewPager)
        adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
    }
}