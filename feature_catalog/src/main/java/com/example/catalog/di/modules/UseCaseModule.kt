package com.example.catalog.di.modules

import com.example.catalog.domain.GetProductListUseCase
import com.example.remote.data.repository.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetListProductUseCase(repository: ProductRepository): GetProductListUseCase =
        GetProductListUseCase(repository)
}