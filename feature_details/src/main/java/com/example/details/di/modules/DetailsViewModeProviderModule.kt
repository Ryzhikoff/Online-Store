package com.example.details.di.modules

import com.example.database.di.DatabaseModule
import com.example.database.di.DatabaseUseCaseModule
import com.example.database.domain.ProductDeleteFromFavoritesUseCase
import com.example.database.domain.ProductSaveToFavoriteUseCase
import com.example.details.utils.DetailsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        DatabaseModule::class,
        DatabaseUseCaseModule::class
    ]
)
class DetailsViewModeProviderModule {
    @Singleton
    @Provides
    fun provideDetailsViewModelFactory(
        productDeleteFromFavoritesUseCase: ProductDeleteFromFavoritesUseCase,
        productSaveToFavoriteUseCase: ProductSaveToFavoriteUseCase

    ): DetailsViewModelFactory =
        DetailsViewModelFactory(
            productDeleteFromFavoritesUseCase, productSaveToFavoriteUseCase
        )
}