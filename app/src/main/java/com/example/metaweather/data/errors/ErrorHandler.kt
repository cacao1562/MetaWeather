package com.example.metaweather.data.errors

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}