package com.example.metaweather.data.entity

data class ResponseLocation(
    val title: String,
    val location_type: String,
    val woeid: Int,
    val latt_long: String,
    val timezone: String,
    val time: String,
    val sun_rise: String,
    val sun_set: String,
    val timezone_name: String,
    val consolidated_weather: List<ConsolidatedWeather>,
    val parent: Parent,
    val sources: List<Source>,
)

data class ConsolidatedWeather(
    val id: Int,
    val weather_state_name: String,
    val weather_state_abbr: String,
    val wind_direction_compass: String,
    val created: String,
    val applicable_date: String,
    val min_temp: Int,
    val max_temp: Int,
    val the_temp: Int,
    val wind_speed: Float,
    val wind_direction: Float,
    val air_pressure: Float,
    val humidity: Float,
    val visibility: Float,
    val predictability: Int
)

data class Parent(
    val title: String,
    val location_type: String,
    val woeid: Int,
    val latt_long: String
)

data class Source(
    val title: String,
    val slug: String,
    val url: String,
    val crawl_rate: Int
)