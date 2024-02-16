package com.example.catalog.di

import com.example.catalog.di.modules.ViewModelFactoryModule
import com.example.catalog.ui.CatalogViewModel
import com.example.catalog.ui.fragments.CatalogFragment
import com.example.database.di.DatabaseModule
import com.example.database.di.DatabaseUseCaseModule
import com.example.remote.di.modules.RemoteModule
import com.example.remote.di.modules.RemoteUseCaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        RemoteModule::class,
        RemoteUseCaseModule::class,
        DatabaseModule::class,
        DatabaseUseCaseModule::class
    ]
)
interface CatalogComponent {
    fun inject(viewModel: CatalogViewModel)
    fun inject(catalogFragment: CatalogFragment)
}