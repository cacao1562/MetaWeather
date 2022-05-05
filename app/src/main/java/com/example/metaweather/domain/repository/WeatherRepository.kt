package com.example.metaweather.domain.repository

import com.example.metaweather.data.entity.ResponseLocation
import com.example.metaweather.data.entity.ResponseLocationSearch
import com.example.metaweather.data.network.BaseResult
import com.example.metaweather.domain.model.LocationInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getLocationSearch(
        onLoading: (Boolean) -> Unit,
        query: String
    ): Flow<BaseResult<List<ResponseLocationSearch>>>

    suspend fun getLocation(
        onLoading: (Boolean) -> Unit,
        woeid: Int
    ): Flow<BaseResult<LocationInfo>>

}