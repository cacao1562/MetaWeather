package com.example.metaweather.data.api

import com.example.metaweather.data.entity.ResponseLocation
import com.example.metaweather.data.entity.ResponseLocationSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("/api/location/search")
    suspend fun getLocationSearch(
        @Query("query") query: String
    ): Response<List<ResponseLocationSearch>>

    @GET("/api/location/{woeid}")
    suspend fun getLocation(
        @Path("woeid") woeid: Int
    ): Response<ResponseLocation>

}