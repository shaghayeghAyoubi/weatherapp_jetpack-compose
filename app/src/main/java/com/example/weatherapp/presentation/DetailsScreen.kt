package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter


// ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.weatherapp.domain.model.ForecastResponse
import com.example.weatherapp.presentation.utils.UiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    city: String,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
//    val forecastState by viewModel.forecastState.collectAsState()
//    val isLoading by viewModel.isLoading.collectAsState()
//    val error by viewModel.errorMessage.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(city) {
        viewModel.fetchForecast(city)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Forecast for $city")},
                navigationIcon  = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        padding -> when(uiState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
         is UiState.Error -> {
             val message = (uiState as UiState.Error).message
             Box(
                 modifier = Modifier
                     .fillMaxSize()
                     .padding(padding),
                 contentAlignment  = Alignment.Center
             ) {
                 Text("Error: $message", color = Color.Red)
             }
         }


        is UiState.Success -> {
            val forecast = (uiState as UiState.Success<ForecastResponse>).data
            LazyColumn(modifier = Modifier.padding(padding)) {
                itemsIndexed(forecast.forecastDays) {index, day ->
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
                        if (index < forecast.forecastDays.lastIndex) {
                            Divider(modifier = Modifier.padding(vertical = 8.dp))
                        }
                    }
                }
            }}
        }
    }

//    when {
//        isLoading -> {
//            CircularProgressIndicator()
//        }
//
//        error != null -> {
//            Text("Error: $error")
//        }
//
//        forecastState != null -> {
//            LazyColumn {
//                items(forecastState!!.forecastDays) { day ->
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp)
//                    ) {
//                        Text(text = day.date, fontWeight = FontWeight.Bold)
//                        Text(text = "Avg temp: ${day.avgTempCelsius}°C")
//                        Row(verticalAlignment = Alignment.CenterVertically) {
//                            Image(
//                                painter = rememberAsyncImagePainter(day.condition.iconUrl),
//                                contentDescription = null,
//                                modifier = Modifier.size(40.dp)
//                            )
//                            Spacer(Modifier.width(8.dp))
//                            Text(text = day.condition.text)
//                        }
//                        Divider(modifier = Modifier.padding(vertical = 8.dp))
//                    }
//                }
//            }
//        }
//    }
}
