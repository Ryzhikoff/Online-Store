package com.example.setting_provider

import android.content.Context

import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit

class SettingProviderImpl(applicationContext: Context) : SettingProvider {

    private val sharedPreferences = applicationContext.getSharedPreferences(FILE_NAME, MODE_PRIVATE)

    override fun saveUserData(userData: UserData) {
        userData.apply {
            sharedPreferences.edit {
                putString(FIELD_NAME, name)
                putString(FIELD_SURNAME, surname)
                putString(FIELD_PHONE_NUMBER, phoneNumber)
                apply()
            }
        }
    }

    override fun getUserData(): UserData =
        UserData(
            name = sharedPreferences.getString(FIELD_NAME, "")!!,
            surname = sharedPreferences.getString(FIELD_SURNAME, "")!!,
            phoneNumber = sharedPreferences.getString(FIELD_PHONE_NUMBER, "")!!
        )


    override fun isRegistrationComplete(): Boolean =
        sharedPreferences.getBoolean(FIELD_REGISTRATION_COMPLETE, false)

    override fun registrationComplete() {
        sharedPreferences.edit().apply {
            putBoolean(FIELD_REGISTRATION_COMPLETE, true)
            apply()
        }
    }

    override fun clearData() {
        sharedPreferences.edit {
            clear()
            apply()
        }
    }

    private companion object {
        const val FILE_NAME = "settings"
        const val FIELD_NAME = "name"
        const val FIELD_SURNAME = "surname"
        const val FIELD_PHONE_NUMBER = "number"
        const val FIELD_REGISTRATION_COMPLETE = "registration_complete"
    }
}