package com.example.details.di

import com.example.database.di.modules.DatabaseModule
import com.example.database.di.modules.DatabaseUseCaseModule
import com.example.details.di.modules.DetailsContextProviderModule
import com.example.details.di.modules.DetailsViewModeProviderModule
import com.example.details.ui.DetailViewModel
import com.example.details.ui.DetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        DatabaseUseCaseModule::class,
        DetailsViewModeProviderModule::class,
        DetailsContextProviderModule::class
    ]
)
interface DetailsComponent {
    fun inject(detailsFragment: DetailsFragment)
    fun inject(detailViewModel: DetailViewModel)
}