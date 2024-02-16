package com.example.details.di.modules

import com.example.details.di.DetailsComponent

interface DetailsComponentProvider {
    fun getDetailsComponent(): DetailsComponent
}