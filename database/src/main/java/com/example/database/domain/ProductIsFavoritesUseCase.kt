package com.example.database.domain

import com.example.database.data.repository.DatabaseRepository
import javax.inject.Inject

class ProductIsFavoritesUseCase @Inject constructor(
    private val repository: DatabaseRepository
) {
    suspend fun execute(id: String): Boolean {
        val productIdEntity = repository.getProductEntity(id)
        return productIdEntity != null
    }
}