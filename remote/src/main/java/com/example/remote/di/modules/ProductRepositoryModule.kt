package com.example.remote.di.modules

import com.example.remote.data.api.ProductApi
import com.example.remote.data.repository.ProductRepository
import com.example.remote.data.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module(
    includes = [
        RemoteModule::class
    ]
)
class ProductRepositoryModule {
    @Singleton
    @Provides
    fun provideProductRepository(retrofitService: ProductApi): ProductRepository =
        ProductRepositoryImpl(retrofitService)

}