package com.example.onlinestore.di

import com.example.onlinestore.MainActivity
import com.example.remote.di.modules.ProductRepositoryModule
import com.example.setting_provider.di.SettingProviderModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SettingProviderModule::class,
        ProductRepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}