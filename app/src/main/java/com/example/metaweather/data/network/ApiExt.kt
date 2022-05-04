package com.example.metaweather.data.network

import com.example.metaweather.data.errors.ErrorEntity
import com.example.metaweather.data.errors.ErrorHandler
import retrofit2.Response

suspend fun <T> safeApiCall(errorHandler: ErrorHandler, apiCall: suspend () -> Response<T>): BaseResult<T> {

    try {
        val response = apiCall.invoke()
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                return BaseResult.Success(body)
            }
        }
        return BaseResult.Error(ErrorEntity.ApiError.UnKnown("${response.code()} ${response.message()}"))

    }catch (throwable: Throwable) {
        return BaseResult.Error(errorEntity = errorHandler.getError(throwable))

    } catch (e: Exception) {
        e.cause?.let {
            return BaseResult.Error(errorEntity = errorHandler.getError(it))
        }
        return BaseResult.Error(ErrorEntity.NetWork(e.message ?: e.toString()))
    }

}
