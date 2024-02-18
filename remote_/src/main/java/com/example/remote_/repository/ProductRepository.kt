package com.example.remote_.data.repository

import com.example.remote_.models.ApiResult

interface ProductRepository {
    fun getProductList(): ApiResult<Any>
}