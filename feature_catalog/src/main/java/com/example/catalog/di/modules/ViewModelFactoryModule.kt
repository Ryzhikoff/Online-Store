package com.example.catalog.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.catalog.domain.GetProductListUseCase
import com.example.catalog.utils.CatalogViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(useCase: GetProductListUseCase): CatalogViewModelFactory {
        return CatalogViewModelFactory(useCase)
    }
}