package com.example.catalog.ui

import com.example.remote.models.ApiResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalog.domain.GetProductListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val useCase: GetProductListUseCase
) : ViewModel() {


    private val _apiResult = MutableSharedFlow<ApiResult<Any>>()
    val apiResult = _apiResult.asSharedFlow()

    fun getProductList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = useCase.execute()
            _apiResult.emit(result)
        }
    }

}

