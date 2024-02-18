package com.example.account.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.domain.ClearDatabaseUseCase
import com.example.database.domain.GetCountElementUseCase
import com.example.setting_provider.SettingProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val clearDatabaseUseCase: ClearDatabaseUseCase,
    private val settingProvider: SettingProvider,
    private val getCountElementUseCase: GetCountElementUseCase
) : ViewModel() {

    private var _countElement = MutableSharedFlow<Long>()
    val countElement = _countElement.asSharedFlow()

    fun clearData() {
        viewModelScope.launch(Dispatchers.IO) {
            settingProvider.clearData()
            clearDatabaseUseCase.execute()
        }
    }

    fun getCountElement() {
        viewModelScope.launch(Dispatchers.IO) {
            _countElement.emit(getCountElementUseCase.execute())
        }
    }

}