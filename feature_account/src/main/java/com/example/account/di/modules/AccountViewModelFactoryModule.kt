package com.example.account.di.modules

import com.example.account.utils.AccountViewModelFactory
import com.example.database.di.modules.DatabaseModule
import com.example.database.di.modules.DatabaseUseCaseModule
import com.example.database.domain.ClearDatabaseUseCase
import com.example.database.domain.GetCountElementUseCase
import com.example.setting_provider.SettingProvider
import com.example.setting_provider.di.SettingProviderModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        DatabaseModule::class,
        DatabaseUseCaseModule::class,
        SettingProviderModule::class
    ]
)
class AccountViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideAccountViewModelFactory(
        clearDatabaseUseCase: ClearDatabaseUseCase,
        settingProvider: SettingProvider,
        getCountElementUseCase: GetCountElementUseCase
    ): AccountViewModelFactory =
        AccountViewModelFactory(clearDatabaseUseCase, settingProvider, getCountElementUseCase)
}