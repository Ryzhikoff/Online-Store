package com.example.database.data.repository

import com.example.database.data.room.ProductIdEntity

interface DatabaseRepository {
    suspend fun saveToFavorites(productIdEntity: ProductIdEntity)
    suspend fun deleteFromFavorites(id: String)
    suspend fun getProductEntity(id: String): ProductIdEntity?
    suspend fun clear()
    suspend fun getCountElement(): Long
}