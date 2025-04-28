package com.example.weatherapp.domain.model

data class ForecastResponse(
    val forecastDays: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val avgTemp: Double,
    val conditionText: String,
    val conditionIconUrl: String
)