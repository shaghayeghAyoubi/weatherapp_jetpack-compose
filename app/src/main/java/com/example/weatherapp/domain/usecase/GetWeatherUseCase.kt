package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.data.model.WeatherResponse
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke (city : String): WeatherResponse {
        return repository.getWeather(city)
    }
}