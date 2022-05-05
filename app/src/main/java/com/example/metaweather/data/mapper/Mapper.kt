package com.example.metaweather.data.mapper

import com.example.metaweather.data.entity.ResponseLocation
import com.example.metaweather.domain.model.LocationInfo

fun ResponseLocation.asInfo() =
    LocationInfo(title, woeid, consolidated_weather.take(2))
