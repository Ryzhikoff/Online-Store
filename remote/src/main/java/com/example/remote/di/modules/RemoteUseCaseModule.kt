package com.example.remote.di.modules

import com.example.remote.data.repository.ProductRepository
import com.example.remote.domain.GetProductListUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteUseCaseModule {

    @Singleton
    @Provides
    fun provideGetProductListUseCase(repository: ProductRepository): GetProductListUseCase =
        GetProductListUseCase(repository)
}