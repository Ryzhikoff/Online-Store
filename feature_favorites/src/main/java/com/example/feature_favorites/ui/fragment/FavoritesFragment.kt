package com.example.feature_favorites.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.feature_favorites.R
import com.example.feature_favorites.databinding.FragmentFavoritesBinding
import com.example.feature_favorites.ui.FavoritesAdapter

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!
    private val buttons by lazy {
        listOf(binding.productsButton, binding.brandButton)
    }
    private lateinit var currentButton: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)

        binding.productsButton.isSelected = true
        currentButton =  binding.productsButton
        setClickListeners()
        initViewPager()
    }

    private fun initViewPager() {
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = FavoritesAdapter(
            this,
            listOf(
                ProductsFragment(),
                BrandsFragments()
            )
        )
    }

    private fun setClickListeners() = with(binding) {
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.productsButton.setOnClickListener {
            onSegmentedButtonsClick(it)
        }

        binding.brandButton.setOnClickListener {
            onSegmentedButtonsClick(it)
        }
    }

    private fun onSegmentedButtonsClick(v: View) {
        if (currentButton == v) return
        currentButton = v
        buttons.map { it.isSelected = !it.isSelected }
        if (v.tag == PRODUCTS_TAG) {
            binding.viewPager.currentItem = 0
        } else {
            binding.viewPager.currentItem = 1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private companion object {
        const val PRODUCTS_TAG = "products"
    }
}