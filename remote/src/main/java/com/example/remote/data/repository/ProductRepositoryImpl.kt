package com.example.remote.data.repository
import com.example.remote.models.ApiResult
import com.example.remote.data.api.ProductApi
import com.example.remote.data.dto.ProductListDto
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val retrofitService: ProductApi
) : ProductRepository {
    override suspend fun getProductList(): ApiResult<Any> =
        try {
            val response = retrofitService.getProductList()
            println(response)
            if (response.isSuccessful && response.body() != null) {
                ApiResult.Success(response.body() as ProductListDto)
            } else {
                ApiResult.Error("Error response from server")
            }
        } catch (e: Exception) {
            println("Exiption $e")
            ApiResult.Error(e.toString())
        }
}