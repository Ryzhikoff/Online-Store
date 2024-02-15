package com.example.remote.data.api
import com.example.remote.data.dto.ProductListDto
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("/v3/97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getProductList(): Response<ProductListDto>
}