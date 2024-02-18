package com.example.catalog.di.modules

import com.example.remote.domain.GetProductListUseCase
import com.example.database.domain.ProductDeleteFromFavoritesUseCase
import com.example.database.domain.ProductSaveToFavoriteUseCase
import com.example.catalog.utils.CatalogViewModelFactory
import com.example.database.domain.ProductIsFavoritesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(
        getProductListUseCase: GetProductListUseCase,
        productDeleteFromFavoritesUseCase: ProductDeleteFromFavoritesUseCase,
        productSaveToFavoriteUseCase: ProductSaveToFavoriteUseCase,
        isFavoritesUseCase: ProductIsFavoritesUseCase
    ): CatalogViewModelFactory {
        return CatalogViewModelFactory(
            getProductListUseCase, productDeleteFromFavoritesUseCase, productSaveToFavoriteUseCase, isFavoritesUseCase
        )
    }
}