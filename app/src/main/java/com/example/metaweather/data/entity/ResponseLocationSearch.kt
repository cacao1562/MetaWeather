package com.example.metaweather.data.entity

data class ResponseLocationSearch(
    val title: String,
    val location_type: String,
    val woeid: Int,
    val latt_long: String
)