package com.example.database.data.repository

import com.example.database.data.room.ProductIdDao
import com.example.database.data.room.ProductIdEntity
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val productIdDao: ProductIdDao
) : DatabaseRepository {
    override suspend fun saveToFavorites(productIdEntity: ProductIdEntity) {
        productIdDao.insertProductId(productIdEntity)
    }

    override suspend fun deleteFromFavorites(id: String) {
        productIdDao.deleteProductId(id)
    }

    override suspend fun getProductEntity(id: String): ProductIdEntity? =
        productIdDao.getProductIdById(id)

    override suspend fun clear() {
        productIdDao.deleteAll()
    }

    override suspend fun getCountElement(): Long =
        productIdDao.getCountElement()

}