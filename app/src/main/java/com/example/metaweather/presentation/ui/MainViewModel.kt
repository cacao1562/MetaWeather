package com.example.metaweather.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metaweather.data.entity.ResponseLocationSearch
import com.example.metaweather.data.network.BaseResult
import com.example.metaweather.domain.usecase.GetLocationSearchUseCase
import com.example.metaweather.domain.usecase.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.metaweather.domain.model.LocationInfo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLocationSearchUseCase: GetLocationSearchUseCase,
    private val getLocationUseCase: GetLocationUseCase
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val onLoading: (Boolean) -> Unit = {
        _isLoading.value = it
    }

    private val locationSearch: MutableStateFlow<List<ResponseLocationSearch>> = MutableStateFlow(emptyList())

    private val _locationInfo: MutableStateFlow<List<LocationInfo>> = MutableStateFlow(emptyList())
    val locationInfo = _locationInfo.asStateFlow()


    fun getLocationSearch(query: String = "se") {
        locationSearch.value = emptyList()
        _locationInfo.value = emptyList()
        viewModelScope.launch {
            getLocationSearchUseCase(onLoading, query).collect { searchResult ->
                when (searchResult) {
                    is BaseResult.Success -> {
                        locationSearch.value = searchResult.data
                    }
                    is BaseResult.Error -> {
                        Log.d("BaseResult.Error Search = ", searchResult.errorEntity.message)
                    }
                }
            }
        }
    }

    private suspend fun getLocation(woeid: Int) =
        viewModelScope.async {
            getLocationUseCase(onLoading, woeid).first()
        }

    private suspend fun parseLocationInfo(data: List<ResponseLocationSearch>) {
        val time = measureTimeMillis {
            val locationResult = data.map {
                getLocation(it.woeid)
            }.awaitAll()
            val list = locationResult.map { baseResult ->
                when (baseResult) {
                    is BaseResult.Success -> {
                        baseResult.data
                    }
                    is BaseResult.Error -> {
                        Log.d("BaseResult.Error Location = ", baseResult.errorEntity.message)
                        null
                    }
                }
            }.toMutableList()
            list.add(0, LocationInfo("", 0, emptyList()))
            _locationInfo.value = list.filterNotNull()
        }
        Log.d("BaseResult time = ", "$time")
    }

    init {
        getLocationSearch()
        viewModelScope.launch {
            locationSearch.collect(::parseLocationInfo)
        }
    }

}