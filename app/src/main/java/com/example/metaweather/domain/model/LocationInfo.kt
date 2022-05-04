package com.example.metaweather.domain.model

import com.example.metaweather.data.entity.ConsolidatedWeather

data class LocationInfo(
    val title: String,
    val woeid: Int,
    val locations: List<ConsolidatedWeather>
)
