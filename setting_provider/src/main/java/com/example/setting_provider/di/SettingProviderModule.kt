package com.example.setting_provider.di

import android.content.Context
import com.example.setting_provider.SettingProvider
import com.example.setting_provider.SettingProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SettingProviderModule(private val applicationContext: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context = applicationContext

    @Singleton
    @Provides
    fun provideSettingProvider(applicationContext: Context): SettingProvider = SettingProviderImpl(applicationContext)
}