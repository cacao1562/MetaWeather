package com.example.metaweather.domain.usecase

import com.example.metaweather.data.entity.ResponseLocation
import com.example.metaweather.data.network.BaseResult
import com.example.metaweather.domain.model.LocationInfo
import com.example.metaweather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val repository: WeatherRepository
){
    suspend operator fun invoke(
        onLoading: (Boolean) -> Unit,
        woeid: Int
    ): Flow<BaseResult<LocationInfo>> {
        return repository.getLocation(onLoading, woeid)
    }

}