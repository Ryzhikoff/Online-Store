package com.example.account.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.account.ui.AccountViewModel
import com.example.database.domain.ClearDatabaseUseCase
import com.example.database.domain.GetCountElementUseCase
import com.example.setting_provider.SettingProvider
import javax.inject.Inject

class AccountViewModelFactory @Inject constructor(
    private val clearDatabaseUseCase: ClearDatabaseUseCase,
    private val settingProvider: SettingProvider,
    private val getCountElementUseCase: GetCountElementUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        println("DetailsViewModelFactory")
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AccountViewModel(
                clearDatabaseUseCase,
                settingProvider,
                getCountElementUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}