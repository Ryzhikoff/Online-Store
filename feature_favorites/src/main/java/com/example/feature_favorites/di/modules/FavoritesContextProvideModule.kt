package com.example.feature_favorites.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FavoritesContextProvideModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context = context
}