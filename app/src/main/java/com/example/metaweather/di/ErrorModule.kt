package com.example.metaweather.di

import android.content.Context
import com.example.metaweather.data.errors.ErrorHandler
import com.example.metaweather.data.errors.ErrorHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class ErrorModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): ErrorHandler {
        return ErrorHandlerImpl(context)
    }

}