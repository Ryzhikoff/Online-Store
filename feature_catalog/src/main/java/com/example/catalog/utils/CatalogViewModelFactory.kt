package com.example.catalog.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catalog.domain.GetProductListUseCase
import com.example.catalog.ui.CatalogViewModel
import javax.inject.Inject

class CatalogViewModelFactory @Inject constructor(
    private val useCase: GetProductListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatalogViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}