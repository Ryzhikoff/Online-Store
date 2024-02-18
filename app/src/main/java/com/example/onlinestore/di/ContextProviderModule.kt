package com.example.onlinestore.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextProviderModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideContext(): Context = context
}