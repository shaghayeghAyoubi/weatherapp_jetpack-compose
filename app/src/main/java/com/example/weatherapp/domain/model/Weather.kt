package com.example.weatherapp.domain.model

import com.example.weatherapp.data.model.WeatherConditionDto

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String
)

data class CurrentWeather(
    val tempC: Double,
    val condition: WeatherConditionDto,
    val humidity: Int,
    val windKph: Double
)

data class WeatherCondition(
    val text: String,
    val icon: String
)