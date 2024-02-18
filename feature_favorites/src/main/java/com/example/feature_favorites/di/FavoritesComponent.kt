package com.example.feature_favorites.di

import com.example.database.di.modules.DatabaseModule
import com.example.database.di.modules.DatabaseUseCaseModule
import com.example.feature_favorites.di.modules.FavoritesContextProvideModule
import com.example.feature_favorites.di.modules.ProductViewModelFactoryModule
import com.example.feature_favorites.ui.ProductViewModel
import com.example.feature_favorites.ui.fragment.ProductsFragment
import com.example.remote.di.modules.RemoteModule
import com.example.remote.di.modules.RemoteUseCaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ProductViewModelFactoryModule::class,
        FavoritesContextProvideModule::class,
        RemoteModule::class,
        RemoteUseCaseModule::class,
        DatabaseModule::class,
        DatabaseUseCaseModule::class,
    ]
)
interface FavoritesComponent {
    fun inject(productsFragment: ProductsFragment)

    fun inject(productViewModel: ProductViewModel)
}