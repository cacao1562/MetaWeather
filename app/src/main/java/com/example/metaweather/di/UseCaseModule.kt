package com.example.metaweather.di

import com.example.metaweather.domain.repository.WeatherRepository
import com.example.metaweather.domain.usecase.GetLocationSearchUseCase
import com.example.metaweather.domain.usecase.GetLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetLocationSearchUseCase(weatherRepository: WeatherRepository): GetLocationSearchUseCase {
        return GetLocationSearchUseCase(weatherRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetLocationUseCase(weatherRepository: WeatherRepository): GetLocationUseCase {
        return GetLocationUseCase(weatherRepository)
    }

}