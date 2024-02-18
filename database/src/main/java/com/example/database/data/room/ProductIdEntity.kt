package com.example.database.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = DatabaseConstants.TABLE_NAME_PRODUCT_ID,
    indices = [Index(
        value = ["product_id"],
        unique = true
    )]
)
data class ProductIdEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "product_id") val productId: String
)