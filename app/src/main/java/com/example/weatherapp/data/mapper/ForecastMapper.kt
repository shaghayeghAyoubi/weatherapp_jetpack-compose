package com.example.weatherapp.data.mapper

import com.example.weatherapp.data.model.CurrentWeatherDto
import com.example.weatherapp.data.model.ForecastDto
import com.example.weatherapp.data.model.ForecastResponseDto
import com.example.weatherapp.data.model.WeatherResponseDto
import com.example.weatherapp.domain.model.Condition
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.ForecastDay
import com.example.weatherapp.domain.model.ForecastResponse
import com.example.weatherapp.domain.model.WeatherResponse

fun ForecastResponseDto.toDomain(): ForecastResponse {
    val days = this.forecast?.forecastDay ?.map { dayDto ->
        ForecastDay(
            date = dayDto.date,
            avgTempCelsius = dayDto.day.avgTempC,
            condition = Condition(
                text = dayDto.day.condition.text,
                // Prefix "https:" to form a valid URL from the API’s icon path
                iconUrl = "https:" + dayDto.day.condition.icon
            )
        )
    } ?: emptyList()
    return ForecastResponse(forecastDays = days)
}


