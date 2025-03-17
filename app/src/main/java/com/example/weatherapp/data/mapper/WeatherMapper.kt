package com.example.weatherapp.data.mapper


import com.example.weatherapp.data.model.CurrentWeatherDto
import com.example.weatherapp.data.model.LocationDto
import com.example.weatherapp.data.model.WeatherConditionDto
import com.example.weatherapp.domain.model.Location
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.WeatherCondition

fun CurrentWeatherDto.toDomain() : CurrentWeather {
    return CurrentWeather(
        tempC = tempC,
        condition = condition,
        humidity = humidity,
        windKph = windKph,
    )
}

fun WeatherConditionDto.toDomain() : WeatherCondition {
    return WeatherCondition(
        text = text,
        icon = icon
    )
}

fun LocationDto.toDomain() : Location {
    return Location(
        name = name,
        region = region,
        country = country,
        localtime = localtime,
    )
}