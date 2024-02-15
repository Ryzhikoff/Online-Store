package com.example.remote.models
sealed class ApiResult<out T> {
    data class Success<out R>(val value: R): ApiResult<R>()
    data class Error(
        val errorBody: String?
    ): ApiResult<Nothing>()
}

inline fun <reified T> ApiResult<T>.updateAdapterList(callback: (value: T) -> Unit) {
    if (this is ApiResult.Success) {
        callback(value)
    }
}

inline fun <reified T> ApiResult<T>.doOnError(callback: (errorBody: String?) -> Unit) {
    if (this is ApiResult.Error) {
        callback(errorBody)
    }
}