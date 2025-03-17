package com.example.weatherapp.domain.repository



import com.example.weatherapp.data.model.WeatherResponse

interface WeatherRepository {
    suspend fun getWeather(city: String): WeatherResponse
}