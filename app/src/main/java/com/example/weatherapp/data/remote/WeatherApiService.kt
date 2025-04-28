package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.ForecastDto
import com.example.weatherapp.data.model.WeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String
    ): WeatherResponseDto


    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("days") days: Int = 3 // Optional, default 3 days
    ): ForecastDto
}