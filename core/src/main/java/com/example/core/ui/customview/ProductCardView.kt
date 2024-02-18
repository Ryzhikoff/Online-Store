package com.example.core.ui.customview

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.core.R
import com.example.core.databinding.ProductCardBinding
import com.example.core.ui.viewpager.VpAdapter
import com.example.core.models.ProductUi

class ProductCardView(context: Context) : ConstraintLayout(context) {

    private val binding: ProductCardBinding
    private var productUi: ProductUi? = null

    init {
        binding = ProductCardBinding.bind(inflate(context, R.layout.product_card, this))
    }

    constructor(
        fragment: Fragment,
        productUi: ProductUi
    ) : this(fragment.requireContext()) {
        init(fragment, productUi)
    }

    fun init(fragment: Fragment, productUi: ProductUi) {
        this.productUi = productUi
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

        addFavoriteButton.isSelected = productUi.isFavorite
    }

    private fun initViewpager(fragment: Fragment, productUi: ProductUi) {

        val adapter = VpAdapter(fragment, productUi.drawableResIds)
        binding.viewPager.adapter = adapter

        binding.indicator.setViewPager(binding.viewPager)
        adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
    }

    fun setOnFavoriteClickListener(onClick: (ProductUi) -> Unit) {
        binding.addFavoriteButton.setOnClickListener {
            productUi!!.isFavorite = !productUi!!.isFavorite
            binding.addFavoriteButton.isSelected = !binding.addFavoriteButton.isSelected
            onClick(productUi!!)
        }
    }
}