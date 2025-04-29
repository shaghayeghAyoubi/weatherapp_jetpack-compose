package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter


// ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun DetailScreen(
    city: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val forecastState by viewModel.forecastState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(city) {
        viewModel.fetchForecast(city)
    }

    when {
        isLoading -> {
            CircularProgressIndicator()
        }

        error != null -> {
            Text("Error: $error")
        }

        forecastState != null -> {
            LazyColumn {
                items(forecastState!!.forecastDays) { day ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = day.date, fontWeight = FontWeight.Bold)
                        Text(text = "Avg temp: ${day.avgTempCelsius}°C")
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = rememberAsyncImagePainter(day.condition.iconUrl),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(text = day.condition.text)
                        }
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }
        }
    }
}
