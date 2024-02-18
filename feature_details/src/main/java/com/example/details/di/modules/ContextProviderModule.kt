package com.example.details.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailsContextProviderModule(private val applicationContext: Context) {
    @Singleton
    @Provides
    fun provideContext(): Context = applicationContext
}