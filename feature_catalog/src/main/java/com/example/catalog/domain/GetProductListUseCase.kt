package com.example.catalog.domain

import com.example.catalog.models.toProductUiList
import com.example.remote.data.dto.ProductListDto
import com.example.remote.models.ApiResult
import com.example.remote.data.repository.ProductRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend fun execute() : ApiResult<Any> =
        coroutineScope {
            val deferred = async {
                repository.getProductList()
            }

            val result = deferred.await()

            when (result) {
                is ApiResult.Success -> {
                    val uiList = (result.value as ProductListDto).toProductUiList()
                    ApiResult.Success(uiList)
                }
                is ApiResult.Error -> result
            }

        }

}