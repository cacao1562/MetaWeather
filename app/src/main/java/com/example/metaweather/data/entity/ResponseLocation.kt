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
    val id: Double,
    val weather_state_name: String,
    val weather_state_abbr: String,
    val wind_direction_compass: String,
    val created: String,
    val applicable_date: String,
    val min_temp: Double,
    val max_temp: Double,
    val the_temp: Double,
    val wind_speed: Double,
    val wind_direction: Double,
    val air_pressure: Double,
    val humidity: Double,
    val visibility: Double,
    val predictability: Double
) {
    val displayTemp = "${the_temp.toInt()}°C"
    val displayHumidity = "${humidity.toInt()}%"
}

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