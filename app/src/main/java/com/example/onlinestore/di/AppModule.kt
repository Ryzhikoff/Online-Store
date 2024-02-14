package com.example.onlinestore.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val applicationContext: Context) {

    @Singleton
    @Provides
    fun provideContext(applicationContext: Context): Context = applicationContext
}