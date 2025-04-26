package com.example.weatherapp.domain.repository



import com.example.weatherapp.data.model.WeatherResponseDto
import com.example.weatherapp.domain.model.WeatherResponse
import javax.inject.Singleton

@Singleton
interface WeatherRepository {
    suspend fun getWeather(city: String): WeatherResponse
}