package com.example.setting_provider

interface SettingProvider {
    fun saveUserData(userData: UserData)
    fun getUserData(): UserData
    fun isRegistrationComplete(): Boolean
    fun registrationComplete()
}