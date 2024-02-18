package com.example.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ArrayRes
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core.extensions.formEnding
import com.example.core.extensions.getParcelableCompat
import com.example.core.models.ProductUi
import com.example.core.ui.viewpager.VpAdapter
import com.example.details.R
import com.example.details.databinding.FragmentDetailsBinding
import com.example.details.di.DetailsComponentProvider
import com.example.details.ui.cistomview.CharacteristicsView
import com.example.details.utils.DetailsViewModelFactory
import javax.inject.Inject

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!
    private lateinit var productUi: ProductUi

    @Inject
    lateinit var viewModelFactory: DetailsViewModelFactory
    private val viewModel: DetailViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (requireActivity().application as DetailsComponentProvider)
            .getDetailsComponent()
            .inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        arguments?.getParcelableCompat<ProductUi>(KEY_PRODUCT_UI)?.let {
            this.productUi = it
        }
        initViewpager()
        setData()
        setClickListeners()
        binding.ingredients.doOnLayout {
            checkTextAllVisible()
        }
    }


    private fun initViewpager() {

        val adapter = VpAdapter(this, productUi.drawableResIds)
        binding.viewPager.adapter = adapter

        binding.indicator.setViewPager(binding.viewPager)
        adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
    }

    private fun setData() {
        with(binding) {
            title.text = productUi.title
            subtitle.text = productUi.subtitle

            addFavoriteButton.isSelected = productUi.isFavorite

            val countRating = productUi.feedbackCounts.toString()
            val availableText = getString(
                R.string.avaliable, "$countRating ${countRating.last().formEnding(resources, R.array.counters)}"
            )
            available.text = availableText

            productUi.rating?.let {
                binding.ratingContainer.isVisible = true
                starsView.setRating(it)
                rating.text = productUi.rating.toString()

                val countFeedbacks = productUi.feedbackCounts.toString()
                val feedbacksText = "$countFeedbacks ${countFeedbacks.last().formEnding(resources, R.array.feedbacks)}"
                feedbacks.text = feedbacksText
            }

            val priceText = "${productUi.newPrice} ${productUi.unit}"
            newPrice.text = priceText

            val oldPriceText = "${productUi.oldPrice}${productUi.unit}"
            oldPrice.text = oldPriceText
            val discountText = "-${productUi.discount}%"
            discount.text = discountText

            brandButtonName.text = productUi.title
            description.text = productUi.description

            productUi.info.forEach { pair ->
                val characteristicsView = CharacteristicsView(requireContext(), pair.first, pair.second)
                characteristicsContainer.addView(characteristicsView)
            }

            ingredients.text = productUi.ingredients

            val newPriceInButtonText = "${productUi.newPrice}${productUi.unit}"
            newPriceInButton.text = newPriceInButtonText
            oldPriceInButton.text = oldPriceText
        }
    }


    private fun setClickListeners() = with(binding) {
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        hideAndShowDescription.setOnClickListener {
            onHideAndShowDescriptionClick()
        }

        hideAndShowIngredients.setOnClickListener { button ->
            button as TextView
            onHideAndShowIngredientsClick(button)
        }

        addFavoriteButton.setOnClickListener {
            onFavoriteButtonClick()
        }
    }

    private fun onHideAndShowDescriptionClick() = with(binding) {
        enableBrandAndDescription(!brandButton.isVisible)
        hideAndShowDescription.text =
            getString(
                if (brandButton.isVisible)
                    R.string.button_hide
                else
                    R.string.button_show
            )
    }

    private fun enableBrandAndDescription(value: Boolean) = with(binding) {
        brandButton.isVisible = value
        description.isVisible = value
    }

    private fun onHideAndShowIngredientsClick(button: TextView) = with(binding) {
        if (ingredients.maxLines == 2) {
            button.text = getString(R.string.button_hide)
            ingredients.maxLines = Int.MAX_VALUE
        } else if (ingredients.maxLines == Int.MAX_VALUE) {
            button.text = getString(R.string.button_show)
            ingredients.maxLines = 2
        }
    }


    private fun onFavoriteButtonClick() {
        if (productUi.isFavorite)
            viewModel.deleteFromFavorites(productUi)
        else
            viewModel.saveToFavorite(productUi)

        productUi.isFavorite = !productUi.isFavorite
        binding.addFavoriteButton.isSelected = !binding.addFavoriteButton.isSelected

    }

    private fun checkTextAllVisible() {
        val layout = binding.ingredients.layout
        if (layout != null) {
            val lineCount = layout.lineCount
            val lastLineIndex = lineCount - 1
            val lastLineEndPosition = layout.getLineEnd(lastLineIndex)
            val lastLineVisible = layout.getEllipsisCount(lastLineIndex) == 0
            val isTextFullyVisible = if (lastLineVisible) {
                lastLineEndPosition == binding.ingredients.text.length
            } else {
                false
            }
            if (isTextFullyVisible) {
                binding.hideAndShowIngredients.isVisible = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_PRODUCT_UI = "product_ui"
    }
}