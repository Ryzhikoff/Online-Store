package com.example.feature_favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_favorites.models.toProductUiList
import com.example.core.models.ProductUi
import com.example.database.domain.ProductDeleteFromFavoritesUseCase
import com.example.database.domain.ProductIsFavoritesUseCase
import com.example.database.domain.ProductSaveToFavoriteUseCase
import com.example.remote.data.dto.ProductListDto
import com.example.remote.domain.GetProductListUseCase
import com.example.remote.models.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductListUseCase,
    private val deleteUseCase: ProductDeleteFromFavoritesUseCase,
    private val isFavoritesUseCase: ProductIsFavoritesUseCase
) : ViewModel() {


    private val _apiResult = MutableSharedFlow<ApiResult<Any>>()
    val apiResult = _apiResult.asSharedFlow()

    fun getProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getProductsUseCase.execute()

            when (result) {
                is ApiResult.Success -> {
                    val uiList = (result.value as ProductListDto).toProductUiList(isFavoritesUseCase)

                    _apiResult.emit(ApiResult.Success(uiList.filter { it.isFavorite }))
                }

                is ApiResult.Error -> _apiResult.emit(result)

            }
        }
    }


    fun deleteFromFavorites(productUi: ProductUi) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUseCase.execute(productUi.id)
        }
    }

}