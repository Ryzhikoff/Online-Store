package com.example.catalog.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CatalogContextProviderModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideContext(): Context = context
}