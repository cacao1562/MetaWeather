package com.example.metaweather.domain.usecase

import com.example.metaweather.data.entity.ResponseLocationSearch
import com.example.metaweather.data.network.BaseResult
import com.example.metaweather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationSearchUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        onLoading: (Boolean) -> Unit,
        query: String
    ): Flow<BaseResult<List<ResponseLocationSearch>>> {
        return repository.getLocationSearch(onLoading, query)
    }
}