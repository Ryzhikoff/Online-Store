package com.example.remote_.data.repository

import com.example.remote_.models.ApiResult
import retrofit2.Retrofit
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val retrofitService: Retrofit
) : ProductRepository {
    override fun getProductList(): ApiResult<Any> {
        return ApiResult.Success(2)
    }
}