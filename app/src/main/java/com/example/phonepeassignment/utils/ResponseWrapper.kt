package com.example.phonepeassignment.utils

sealed class ResponseWrapper<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): ResponseWrapper<T>(data)
    class Error<T>(message: String): ResponseWrapper<T>(message = message)
    object Loading: ResponseWrapper<Nothing>()
}