package com.techne.jenotes.domain.util

sealed class Resource<out T> {
    data class Loading<out T>(val data: T? = null) : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val code: Int, val error: String) : Resource<Nothing>()
    data class Exception(val exception: Throwable) : Resource<Nothing>()
}