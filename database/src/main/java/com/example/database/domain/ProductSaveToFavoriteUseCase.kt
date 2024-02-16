package com.example.database.domain

import com.example.database.data.repository.DatabaseRepository
import com.example.database.data.room.ProductIdEntity
import javax.inject.Inject

class ProductSaveToFavoriteUseCase @Inject constructor(
    private val repository: DatabaseRepository
) {

    suspend fun execute(id: String) {
        repository.saveToFavorites(ProductIdEntity(productId = id))
    }
}