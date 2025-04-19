package com.example.weatherapp.data.repository

import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.data.remote.WeatherApiService
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService
) : WeatherRepository {

    override suspend fun getWeather(city: String): WeatherResponse {
        return apiService.getWeather("YOUR_API_KEY", city)
    }
}


///New API Key: fb5c4da780974f518fa103102251703
//https://www.weatherapi.com/my/