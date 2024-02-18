package com.example.database.di.modules

import com.example.database.domain.ProductDeleteFromFavoritesUseCase
import com.example.database.domain.ProductIsFavoritesUseCase
import com.example.database.domain.ProductSaveToFavoriteUseCase
import com.example.database.data.repository.DatabaseRepository
import com.example.database.domain.GetCountElementUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        DatabaseModule::class
    ]
)
class DatabaseUseCaseModule {
    @Singleton
    @Provides
    fun provideProductSaveToFavoriteUseCase(repository: DatabaseRepository): ProductSaveToFavoriteUseCase =
        ProductSaveToFavoriteUseCase(repository)

    @Singleton
    @Provides
    fun provideProductDeleteFromFavoritesUseCase(repository: DatabaseRepository): ProductDeleteFromFavoritesUseCase =
        ProductDeleteFromFavoritesUseCase(repository)

    @Singleton
    @Provides
    fun provideProductIsFavoritesUseCase(repository: DatabaseRepository): ProductIsFavoritesUseCase =
        ProductIsFavoritesUseCase(repository)

    @Singleton
    @Provides
    fun provideGetCountElementUseCase(repository: DatabaseRepository): GetCountElementUseCase =
        GetCountElementUseCase(repository)
}