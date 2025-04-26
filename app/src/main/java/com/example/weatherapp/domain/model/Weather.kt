package com.example.weatherapp.domain.model

import com.example.weatherapp.data.model.WeatherConditionDto
data class WeatherResponse(
    val location: Location,
    val current: CurrentWeather,
)
data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String
)

data class CurrentWeather(
    val tempC: Double,
    val condition: WeatherCondition,
    val humidity: Int,
    val windKph: Double
)

data class WeatherCondition(
    val text: String,
    val icon: String
)