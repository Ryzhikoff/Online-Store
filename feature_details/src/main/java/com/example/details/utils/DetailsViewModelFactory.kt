package com.example.details.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.database.domain.ProductDeleteFromFavoritesUseCase
import com.example.database.domain.ProductSaveToFavoriteUseCase
import com.example.details.ui.DetailViewModel
import javax.inject.Inject

class DetailsViewModelFactory @Inject constructor(
    private val productDeleteFromFavoritesUseCase: ProductDeleteFromFavoritesUseCase,
    private val productSaveToFavoriteUseCase: ProductSaveToFavoriteUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        println("DetailsViewModelFactory")
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(
                productDeleteFromFavoritesUseCase,
                productSaveToFavoriteUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}