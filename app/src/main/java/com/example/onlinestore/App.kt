package com.example.onlinestore

import android.app.Application
import com.example.catalog.di.CatalogComponent
import com.example.catalog.di.CatalogComponentProvider
import com.example.catalog.di.DaggerCatalogComponent
import com.example.database.di.DatabaseModule
import com.example.database.di.DatabaseUseCaseModule
import com.example.details.di.DaggerDetailsComponent
import com.example.details.di.DetailsComponent
import com.example.details.di.modules.DetailsComponentProvider
import com.example.onlinestore.di.AppComponent
import com.example.onlinestore.di.AppComponentProvider
import com.example.onlinestore.di.DaggerAppComponent
import com.example.registration.di.DaggerRegistrationComponent
import com.example.registration.di.RegistrationComponent
import com.example.registration.di.RegistrationComponentProvider
import com.example.remote.di.modules.RemoteModule
import com.example.remote.di.modules.RemoteUseCaseModule
import com.example.setting_provider.di.SettingProviderModule

class App : Application(),
    RegistrationComponentProvider,
    AppComponentProvider,
    CatalogComponentProvider,
    DetailsComponentProvider {
    private val settingProviderModule by lazy {
        SettingProviderModule(this)
    }

    private val remoteModule by lazy {
        RemoteModule()
    }
    private val databaseModule by lazy {
        DatabaseModule(this)
    }

    private val databaseUseCaseModule by lazy {
        DatabaseUseCaseModule()
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
            .remoteUseCaseModule(RemoteUseCaseModule())
            .databaseModule(databaseModule)
            .databaseUseCaseModule(databaseUseCaseModule)
            .build()

    override fun getDetailsComponent(): DetailsComponent =
        DaggerDetailsComponent.builder()
            .databaseModule(databaseModule)
            .databaseUseCaseModule(databaseUseCaseModule)
            .build()


}