package com.example.weatherapp.data.repository


import com.example.weatherapp.data.mapper.toDomain
import com.example.weatherapp.data.model.ForecastDto
import com.example.weatherapp.data.model.WeatherResponseDto
import com.example.weatherapp.data.remote.WeatherApiService
import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton  // This should be here to match the scope
class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
    private val apiKey: String
) : WeatherRepository {

    override suspend fun getWeather(apiKey: String,city: String): WeatherResponse {
        return apiService.getWeather("fb5c4da780974f518fa103102251703", city).toDomain()
    }
    override suspend fun getForecast(apiKey: String, city: String, days: Int): ForecastDto {
        return apiService.getForecast(apiKey, city, days)
    }
}


///New API Key: fb5c4da780974f518fa103102251703
//https://www.weatherapi.com/my/