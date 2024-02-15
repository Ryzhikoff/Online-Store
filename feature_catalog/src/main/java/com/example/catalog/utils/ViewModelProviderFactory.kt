package com.example.catalog.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.internal.Provider
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
//class ViewModelProviderFactory @Inject constructor(
//    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
//            modelClass.isAssignableFrom(it.key)
//        }?.value
//        ?: throw IllegalArgumentException("Unknown ViewModel $modelClass. Forgot to provide and map the ViewModelKey for this ViewModel?")
//        @Suppress("UNCHECKED_CAST")
//        return creator.get() as T
//    }
//}