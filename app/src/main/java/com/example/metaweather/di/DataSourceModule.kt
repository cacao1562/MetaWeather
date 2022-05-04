package com.example.metaweather.di

import com.example.metaweather.data.datasource.WeatherDataSource
import com.example.metaweather.data.datasource.WeatherDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindWeatherDataSource(weatherDataSourceImpl: WeatherDataSourceImpl): WeatherDataSource
}