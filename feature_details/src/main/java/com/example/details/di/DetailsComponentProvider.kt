package com.example.details.di

import com.example.details.di.DetailsComponent

interface DetailsComponentProvider {
    fun getDetailsComponent(): DetailsComponent
}