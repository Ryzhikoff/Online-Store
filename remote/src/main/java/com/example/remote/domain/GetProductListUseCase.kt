package com.example.remote.domain

import com.example.remote.models.ApiResult
import com.example.remote.data.repository.ProductRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val repository: ProductRepository,
) {

    suspend fun execute(): ApiResult<Any> =
        coroutineScope {
            val deferred = async {
                repository.getProductList()
            }

            val result = deferred.await()
            result
        }

}

