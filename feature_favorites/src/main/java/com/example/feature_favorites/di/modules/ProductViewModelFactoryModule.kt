package com.example.feature_favorites.di.modules

import com.example.database.domain.ProductDeleteFromFavoritesUseCase
import com.example.database.domain.ProductIsFavoritesUseCase
import com.example.feature_favorites.utils.ProductsViewModelFactory
import com.example.remote.domain.GetProductListUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProductViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideProductViewModelFactory(
        getProductListUseCase: GetProductListUseCase,
        productDeleteFromFavoritesUseCase: ProductDeleteFromFavoritesUseCase,
        isFavoritesUseCase: ProductIsFavoritesUseCase
    ): ProductsViewModelFactory =
        ProductsViewModelFactory(
            getProductListUseCase, productDeleteFromFavoritesUseCase, isFavoritesUseCase
        )
}