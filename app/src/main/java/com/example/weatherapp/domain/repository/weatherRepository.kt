package com.example.weatherapp.domain.repository



import com.example.weatherapp.data.model.ForecastDto
import com.example.weatherapp.data.model.WeatherResponseDto
import com.example.weatherapp.domain.model.WeatherResponse
import javax.inject.Singleton

@Singleton
interface WeatherRepository {
    suspend fun getWeather(apiKey: String,city: String): WeatherResponse
    suspend fun getForecast(apiKey: String, city: String, days: Int): ForecastDto
}