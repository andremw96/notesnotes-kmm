package com.andremw96.notesnotes_kmm.network.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data = data)

    class SuccessDoNothing<T>(data: T) : Resource<T>(data = data)

    class Error<T>(errorMessage: String) : Resource<T>(message = errorMessage)

    class Loading<T> : Resource<T>()

    class Idle<T> : Resource<T>()
}