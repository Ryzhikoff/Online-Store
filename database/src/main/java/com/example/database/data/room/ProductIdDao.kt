package com.example.database.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductIdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductId(productIdEntity: ProductIdEntity)

    @Query("DELETE FROM ${DatabaseConstants.TABLE_NAME_PRODUCT_ID} WHERE product_id LIKE :id")
    fun deleteProductId(id: String)

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_NAME_PRODUCT_ID} WHERE product_id LIKE :id")
    fun getProductIdById(id: String): ProductIdEntity?

    @Query("DELETE FROM ${DatabaseConstants.TABLE_NAME_PRODUCT_ID}")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM ${DatabaseConstants.TABLE_NAME_PRODUCT_ID}")
    fun getCountElement(): Long
}