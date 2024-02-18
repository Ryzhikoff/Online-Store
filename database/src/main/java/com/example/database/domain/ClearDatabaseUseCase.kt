package com.example.database.domain

import com.example.database.data.repository.DatabaseRepository
import javax.inject.Inject

class ClearDatabaseUseCase @Inject constructor(
    private val repository: DatabaseRepository
) {
    suspend fun execute() {
        repository.clear()
    }
}