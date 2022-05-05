package com.example.metaweather.data.repository

import com.example.metaweather.data.datasource.WeatherDataSource
import com.example.metaweather.data.entity.ResponseLocationSearch
import com.example.metaweather.data.mapper.asInfo
import com.example.metaweather.data.network.BaseResult
import com.example.metaweather.domain.model.LocationInfo
import com.example.metaweather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val dataSource: WeatherDataSource
): WeatherRepository {

    override suspend fun getLocationSearch(
        onLoading: (Boolean) -> Unit,
        query: String
    ): Flow<BaseResult<List<ResponseLocationSearch>>> {
        return dataSource.getLocationSearch(onLoading, query)
    }

    override suspend fun getLocation(
        onLoading: (Boolean) -> Unit,
        woeid: Int
    ): Flow<BaseResult<LocationInfo>> {
        val data = dataSource.getLocation(onLoading, woeid).map { baseResult ->
            when(baseResult) {
                is BaseResult.Success -> {
                    BaseResult.Success(baseResult.data.asInfo())
                }
                is BaseResult.Error -> {
                    baseResult
                }
            }
        }
        return data as Flow<BaseResult<LocationInfo>>
    }
}