package com.example.database.domain

import com.example.database.data.repository.DatabaseRepository

class GetCountElementUseCase (
    private val repository: DatabaseRepository
) {

    suspend fun execute(): Long =
        repository.getCountElement()

}