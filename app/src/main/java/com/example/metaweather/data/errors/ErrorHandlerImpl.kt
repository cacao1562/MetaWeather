package com.example.metaweather.data.errors

import android.content.Context
import com.example.metaweather.R
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class ErrorHandlerImpl(
    private val context: Context
): ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.ApiError.NotFound(throwable.message())
                    else -> ErrorEntity.ApiError.UnKnown(throwable.message())
                }
            }

//            is IOException -> ErrorEntity.NetWork(context.resources.getString(R.string.message_network_unstable))
            is IOException -> ErrorEntity.NetWork("IOException ${throwable.cause}")
            else -> ErrorEntity.NetWork("throwable=$throwable")
        }
    }
}