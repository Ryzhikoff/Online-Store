package com.example.catalog.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.remote.domain.GetProductListUseCase
import com.example.database.domain.ProductDeleteFromFavoritesUseCase
import com.example.database.domain.ProductSaveToFavoriteUseCase
import com.example.catalog.ui.CatalogViewModel
import com.example.database.domain.ProductIsFavoritesUseCase
import javax.inject.Inject

class CatalogViewModelFactory @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val productDeleteFromFavoritesUseCase: ProductDeleteFromFavoritesUseCase,
    private val productSaveToFavoriteUseCase: ProductSaveToFavoriteUseCase,
    private val isFavoritesUseCase: ProductIsFavoritesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        println("CatalogViewModelFactory")
        if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatalogViewModel(
                getProductListUseCase,
                productDeleteFromFavoritesUseCase,
                productSaveToFavoriteUseCase,
                isFavoritesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}