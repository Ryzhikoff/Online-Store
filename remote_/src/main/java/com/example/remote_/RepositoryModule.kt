package com.example.remote_

import com.example.remote_.data.repository.ProductRepository
import com.example.remote_.data.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideProductRepository(retrofitService: Retrofit): ProductRepository =
        ProductRepositoryImpl(retrofitService)
}