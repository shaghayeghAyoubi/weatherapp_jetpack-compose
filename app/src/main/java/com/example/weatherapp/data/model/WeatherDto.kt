package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    @SerializedName("location") val location: LocationDto,
    @SerializedName("current") val current: CurrentWeatherDto,
)

data class LocationDto(
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("localtime") val localtime: String
)

data class CurrentWeatherDto(
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("condition") val condition: WeatherConditionDto,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("wind_kph") val windKph: Double
)

data class WeatherConditionDto(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String
)