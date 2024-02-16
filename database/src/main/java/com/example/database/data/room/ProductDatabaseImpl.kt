package com.example.database.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.data.ProductDatabase

@Database(
    entities = [
        ProductIdEntity::class
    ],
    version = DatabaseConstants.VERSION,
    exportSchema = false
)
abstract class ProductDatabaseImpl : RoomDatabase(), ProductDatabase {
    abstract fun productIdDao(): ProductIdDao
}