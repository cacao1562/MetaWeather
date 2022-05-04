package com.example.metaweather.data.datasource

import com.example.metaweather.data.api.WeatherService
import com.example.metaweather.data.entity.ResponseLocation
import com.example.metaweather.data.entity.ResponseLocationSearch
import com.example.metaweather.data.errors.ErrorHandler
import com.example.metaweather.data.network.BaseResult
import com.example.metaweather.data.network.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class WeatherDataSourceImpl @Inject constructor(
    private val service: WeatherService,
    private val errorHandler: ErrorHandler
): WeatherDataSource {

    override suspend fun getLocationSearch(
        onLoading: (Boolean) -> Unit,
        query: String
    ): Flow<BaseResult<List<ResponseLocationSearch>>> {
        return flow {
            emit(safeApiCall(errorHandler) {
                service.getLocationSearch(query)
            })
        }.onStart { onLoading.invoke(true)
        }.onCompletion { onLoading.invoke(false)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getLocation(
        onLoading: (Boolean) -> Unit,
        woeid: Int
    ): Flow<BaseResult<ResponseLocation>> {
        return flow {
            emit(safeApiCall(errorHandler) {
                service.getLocation(woeid)
            })
        }.onStart { onLoading.invoke(true)
        }.onCompletion { onLoading.invoke(false)
        }.flowOn(Dispatchers.IO)
    }

}