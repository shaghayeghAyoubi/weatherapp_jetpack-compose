package com.example.weatherapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.presentation.DetailsScreen
import com.example.weatherapp.presentation.WeatherScreen
import com.example.weatherapp.presentation.utils.slideInFromLeft
import com.example.weatherapp.presentation.utils.slideInFromRight
import com.example.weatherapp.presentation.utils.slideOutToLeft
import com.example.weatherapp.presentation.utils.slideOutToRight

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WeatherNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "weather_screen",
        enterTransition = { slideInFromRight() }, // Using the slideInFromRight transition
        exitTransition = { slideOutToLeft() }, // Using the slideOutToLeft transition
        popEnterTransition = { slideInFromLeft() }, // Using the slideInFromLeft transition for back navigation
        popExitTransition = { slideOutToRight() }  // Using the slideOutToRight transition for back navigation
    ) {
        composable("weather_screen") {
            WeatherScreen(navController)
        }
        composable("details_screen") {
            DetailsScreen()
        }
    }
}