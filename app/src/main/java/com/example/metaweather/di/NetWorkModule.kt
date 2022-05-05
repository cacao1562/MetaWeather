package com.example.metaweather.di

import android.util.Log
import com.example.metaweather.data.api.WeatherService
import com.example.metaweather.presentation.utils.Constants
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor.Logger { message ->
            if (!message.startsWith("{") && !message.startsWith("[")) {
                Log.d("OkHttp", "$message")
                return@Logger
            }
            try {
                val source = Buffer().writeUtf8(message)
                val reader = JsonReader.of(source)
                val value = reader.readJsonValue()
                val adapter = Moshi.Builder().build().adapter<Any>(Any::class.java).indent("    ")
                val result = adapter.toJson(value)
                Log.d("OkHttp", "$result")
            }catch (e: Exception) {
                e.printStackTrace()
                Log.d("OkHttp", "$message")
            }
        }
        val logging = HttpLoggingInterceptor(logger).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val dispatcher = Dispatcher(Executors.newFixedThreadPool(20)).apply {
            maxRequestsPerHost = 20
        }
        val okHttpClient = OkHttpClient.Builder().apply {
            interceptors().add(logging)
            dispatcher(dispatcher)
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
        }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.Base_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

}