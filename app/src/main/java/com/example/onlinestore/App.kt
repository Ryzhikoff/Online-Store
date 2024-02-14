package com.example.onlinestore

import android.app.Application
import com.example.onlinestore.di.AppComponent
import com.example.onlinestore.di.AppComponentProvider
import com.example.onlinestore.di.DaggerAppComponent
import com.example.registration.di.DaggerRegistrationComponent
import com.example.registration.di.RegistrationComponent
import com.example.registration.di.RegistrationComponentProvider
import com.example.setting_provider.di.SettingProviderModule

class App : Application(), RegistrationComponentProvider, AppComponentProvider {
    private val settingProviderModule by lazy {
        SettingProviderModule(this)
    }

    override fun getRegistrationComponent(): RegistrationComponent =
        DaggerRegistrationComponent.builder()
            .settingProviderModule(settingProviderModule)
            .build()

    override fun getAppComponent(): AppComponent =
        DaggerAppComponent.builder()
            .settingProviderModule(settingProviderModule)
            .build()



}