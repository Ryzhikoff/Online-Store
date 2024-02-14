package com.example.registration.di

import com.example.registration.fragments.RegistrationFragment
import com.example.setting_provider.di.SettingProviderModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SettingProviderModule::class
    ]
)
interface RegistrationComponent {
    fun inject (registrationFragment: RegistrationFragment)
}