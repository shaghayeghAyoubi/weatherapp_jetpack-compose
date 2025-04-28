package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.model.CurrentWeatherDto
import com.example.weatherapp.data.model.ForecastDto
import com.example.weatherapp.data.model.ForecastResponseDto
import com.example.weatherapp.data.model.WeatherResponseDto
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.ForecastResponse
import com.example.weatherapp.domain.model.WeatherResponse

fun ForecastResponseDto.toDomain(): ForecastResponse {
    return  ForecastResponse (
        forecastDays = forecast.toDomain(),

    )
}

