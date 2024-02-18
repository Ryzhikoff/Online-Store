package com.example.remote.data.repository

import com.example.remote.models.ApiResult

interface ProductRepository {
    suspend fun getProductList(): ApiResult<Any>
}