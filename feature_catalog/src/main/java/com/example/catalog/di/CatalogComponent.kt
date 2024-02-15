package com.example.catalog.di

import com.example.catalog.di.modules.UseCaseModule
import com.example.catalog.di.modules.ViewModelFactoryModule
import com.example.catalog.ui.CatalogViewModel
import com.example.catalog.ui.fragments.CatalogFragment
import com.example.remote.di.modules.ProductRepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        UseCaseModule::class,
        ViewModelFactoryModule::class,
        ProductRepositoryModule::class,
    ]
)
interface CatalogComponent {
    fun inject(viewModel: CatalogViewModel)
    fun inject(catalogFragment: CatalogFragment)
}