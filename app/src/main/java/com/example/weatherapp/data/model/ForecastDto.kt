package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ForecastResponseDto(
    @SerializedName("forecast") val forecast: ForecastDto
)

data class ForecastDto(
    @SerializedName("forecastday") val forecastDay: List<ForecastDayDto>
)

data class ForecastDayDto(
    @SerializedName("date") val date: String,
    @SerializedName("day") val day: DayDto
)

data class DayDto(
    @SerializedName("avgtemp_c") val avgTempC: Double,
    @SerializedName("condition") val condition: ConditionDto
)

data class ConditionDto(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String
)