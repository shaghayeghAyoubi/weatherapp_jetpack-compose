package com.example.weatherapp.presentation


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherState by viewModel.weatherState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var cityName by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BasicTextField(
            value = cityName,
            onValueChange = { cityName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (cityName.text.isNotEmpty()) {
                viewModel.fetchWeather(cityName.text)
            }
        }) {
            Text(text = "Get Weather")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> {
                CircularProgressIndicator()
            }
            errorMessage != null -> {
                Text(text = errorMessage ?: "Error", color = MaterialTheme.colorScheme.error)
            }
            weatherState != null -> {
                val weather = weatherState!!
                Text(text = "City: ${weather.location.name}")
                Text(text = "Country: ${weather.location.country}")
                Text(text = "Temperature: ${weather.current.tempC} °C")
                Text(text = "Condition: ${weather.current.condition.text}")
                Text(text = "Humidity: ${weather.current.humidity} %")
                Text(text = "Wind: ${weather.current.windKph} km/h")
            }
        }
    }
}
