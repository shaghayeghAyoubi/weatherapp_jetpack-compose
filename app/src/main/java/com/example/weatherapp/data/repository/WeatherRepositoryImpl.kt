package com.example.weatherapp.data.repository


import com.example.weatherapp.data.mapper.toDomain
import com.example.weatherapp.data.model.ForecastDto
import com.example.weatherapp.data.model.WeatherResponseDto
import com.example.weatherapp.data.remote.WeatherApiService
import com.example.weatherapp.domain.model.ForecastResponse
import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton  // This should be here to match the scope
class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
) : WeatherRepository {

    override suspend fun getWeather(city: String): WeatherResponse {
        return apiService.getWeather( city).toDomain()
    }
    override suspend fun getForecast(city: String, days: Int): ForecastResponse {
        return apiService.getForecast( city, days).toDomain()
    }
}


///New API Key: fb5c4da780974f518fa103102251703
//https://www.weatherapi.com/my/