package com.example.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.models.ProductUi
import com.example.database.domain.ProductDeleteFromFavoritesUseCase
import com.example.database.domain.ProductSaveToFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val deleteUseCase: ProductDeleteFromFavoritesUseCase,
    private val saveUseCase: ProductSaveToFavoriteUseCase,
) : ViewModel() {

    fun saveToFavorite(productUi: ProductUi) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUseCase.execute(productUi.id)
        }
    }

    fun deleteFromFavorites(productUi: ProductUi) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUseCase.execute(productUi.id)
        }
    }

}