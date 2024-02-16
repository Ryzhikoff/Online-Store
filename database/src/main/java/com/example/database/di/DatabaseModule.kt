package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.data.ProductDatabase
import com.example.database.data.repository.DatabaseRepository
import com.example.database.data.repository.DatabaseRepositoryImpl
import com.example.database.data.room.ProductDatabaseImpl
import com.example.database.data.room.DatabaseConstants
import com.example.database.data.room.ProductIdDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context = context

    @Singleton
    @Provides
    fun provideProductDatabase(context: Context): ProductDatabase =
        Room.databaseBuilder(
            context,
            ProductDatabaseImpl::class.java,
            DatabaseConstants.NAME
        ).build()

    @Singleton
    @Provides
    fun provideProductIdDao(database: ProductDatabase): ProductIdDao =
        (database as ProductDatabaseImpl).productIdDao()

    @Singleton
    @Provides
    fun providerDatabaseRepository(productIdDao: ProductIdDao): DatabaseRepository =
        DatabaseRepositoryImpl(productIdDao)

}