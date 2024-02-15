package com.example.remote_

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        RepositoryModule::class
    ]
)
interface RemoteComponent {
}