package com.example.feature_favorites.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.database.domain.ProductDeleteFromFavoritesUseCase
import com.example.database.domain.ProductIsFavoritesUseCase
import com.example.feature_favorites.ui.ProductViewModel
import com.example.remote.domain.GetProductListUseCase
import javax.inject.Inject

class ProductsViewModelFactory @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val productDeleteFromFavoritesUseCase: ProductDeleteFromFavoritesUseCase,
    private val isFavoritesUseCase: ProductIsFavoritesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        println("CatalogViewModelFactory")
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(
                getProductListUseCase,
                productDeleteFromFavoritesUseCase,
                isFavoritesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}