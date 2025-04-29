package com.example.weatherapp.domain.model

data class ForecastResponse(
    val forecastDays: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val avgTempCelsius: Double,
    val condition: Condition
)

data class Condition(
    val text: String,
    val iconUrl: String
)