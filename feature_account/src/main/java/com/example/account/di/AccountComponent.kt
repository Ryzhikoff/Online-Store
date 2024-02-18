package com.example.account.di

import com.example.account.di.modules.AccountViewModelFactoryModule
import com.example.account.ui.AccountViewModel
import com.example.account.ui.fragments.AccountFragment
import com.example.database.di.modules.DatabaseModule
import com.example.database.di.modules.DatabaseUseCaseModule
import com.example.setting_provider.di.SettingProviderModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AccountViewModelFactoryModule::class,
        DatabaseModule::class,
        DatabaseUseCaseModule::class,
        SettingProviderModule::class
    ]
)
interface AccountComponent {
    fun inject(accountFragment: AccountFragment)
    fun inject(accountViewModel: AccountViewModel)
}