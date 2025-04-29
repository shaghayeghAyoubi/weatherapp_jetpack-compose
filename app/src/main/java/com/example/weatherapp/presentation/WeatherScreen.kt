package com.example.weatherapp.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.domain.model.WeatherResponse
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()

) {
    val weatherSate by viewModel.weatherState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var cityName by remember { mutableStateOf(TextFieldValue("")) }
    var snackbarHostState  = remember { SnackbarHostState() }

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            message -> snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)}
    ) {  padding ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){

            OutlinedTextField(
                value = cityName,
                onValueChange = { cityName = it},
                label = { Text("Enter City Name")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if(cityName.text.isNotEmpty()) {
                        viewModel.fetchWeather(cityName.text)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Get Weather")
            }

            Spacer(modifier = Modifier.height(32.dp))

            when {
                isLoading -> {
                    ShimmerWeatherCard()
                }

                weatherSate != null -> {
                    WeatherInfo(weather = weatherSate!!, navController = navController)
                }
            }

        }
    }
}

@Composable
fun WeatherInfo(weather: WeatherResponse, navController: NavController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("details_screen/${weather.location.name}")

            }



    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = rememberAsyncImagePainter("https:${weather.current.condition.icon}"),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = weather.location.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = weather.location.country,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${weather.current.tempC}°C",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = weather.current.condition.text,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Humidity: ${weather.current.humidity}%",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Wind: ${weather.current.windKph} km/h",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
@Composable
fun ShimmerWeatherCard() {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View)

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .shimmer(shimmerInstance)
          ,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {}
}
