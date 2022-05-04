package com.example.metaweather.data.datasource

import com.example.metaweather.data.entity.ResponseLocation
import com.example.metaweather.data.entity.ResponseLocationSearch
import com.example.metaweather.data.network.BaseResult
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {

    suspend fun getLocationSearch(
        onLoading: (Boolean) -> Unit,
        query: String
    ): Flow<BaseResult<List<ResponseLocationSearch>>>

    suspend fun getLocation(
        onLoading: (Boolean) -> Unit,
        woeid: Int
    ): Flow<BaseResult<ResponseLocation>>

}