package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.model.ForecastResponse
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.model.WeatherResponse
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke (city : String): WeatherResponse {
        return repository.getWeather(city)
    }
    suspend operator fun invoke (city : String,days: Int): ForecastResponse {
        return repository.getForecast(city, days)
    }
}