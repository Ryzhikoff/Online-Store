package com.example.onlinestore

import android.app.Application
import com.example.catalog.di.CatalogComponent
import com.example.catalog.di.CatalogComponentProvider
import com.example.catalog.di.DaggerCatalogComponent
import com.example.catalog.di.modules.UseCaseModule
import com.example.onlinestore.di.AppComponent
import com.example.onlinestore.di.AppComponentProvider
import com.example.onlinestore.di.DaggerAppComponent
import com.example.registration.di.DaggerRegistrationComponent
import com.example.registration.di.RegistrationComponent
import com.example.registration.di.RegistrationComponentProvider
import com.example.remote.di.modules.RemoteModule
import com.example.remote.di.modules.ProductRepositoryModule
import com.example.setting_provider.di.SettingProviderModule

class App : Application(), RegistrationComponentProvider, AppComponentProvider, CatalogComponentProvider {
    private val settingProviderModule by lazy {
        SettingProviderModule(this)
    }

    private val productRepositoryModule by lazy {
        ProductRepositoryModule()
    }
    private val remoteModule by lazy {
        RemoteModule()
    }

    override fun getRegistrationComponent(): RegistrationComponent =
        DaggerRegistrationComponent.builder()
            .settingProviderModule(settingProviderModule)
            .build()

    override fun getAppComponent(): AppComponent =
        DaggerAppComponent.builder()
            .settingProviderModule(settingProviderModule)
            .build()

    override fun getCatalogComponentProvider(): CatalogComponent =
        DaggerCatalogComponent.builder()
            .remoteModule(remoteModule)
            .productRepositoryModule(productRepositoryModule)
            .useCaseModule(UseCaseModule())
            .build()

}