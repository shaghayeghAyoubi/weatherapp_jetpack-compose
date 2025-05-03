package com.example.weatherapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.weatherapp.presentation.DetailScreen
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
        enterTransition = { slideInFromRight() },
        exitTransition = { slideOutToLeft() },
        popEnterTransition = { slideInFromLeft() },
        popExitTransition = { slideOutToRight() }
    ) {
        composable("weather_screen") {
            WeatherScreen(navController)
        }
        composable(
            "details_screen/{cityName}",
            arguments = listOf(navArgument("cityName") { type = NavType.StringType })
        ) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            DetailScreen(cityName, navController) // 👈 Pass it to your screen
        }
    }
}