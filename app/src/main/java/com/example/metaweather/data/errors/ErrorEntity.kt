package com.example.metaweather.data.errors


sealed class ErrorEntity(
    open val message: String = ""
) {
    sealed class ApiError : ErrorEntity() {
        class NotFound(override val message: String) : ErrorEntity(message)
        class UnKnown(override val message: String) : ErrorEntity(message)
    }

    class NetWork(override val message: String) : ErrorEntity(message)

}