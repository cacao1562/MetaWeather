package com.example.metaweather.data.network

import com.example.metaweather.data.errors.ErrorEntity

sealed class BaseResult<T> {
    data class Success<T>(val data: T) : BaseResult<T>()
    data class Error<T>(val errorEntity: ErrorEntity) : BaseResult<T>()
}