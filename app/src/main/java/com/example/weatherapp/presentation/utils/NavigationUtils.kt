package com.example.weatherapp.presentation.utils

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

fun slideInFromRight(): EnterTransition =
    slideInHorizontally(
        initialOffsetX = { 1000 }, // Slide from the right
        animationSpec = tween(700) // Duration of the transition
    )

// Slide out to the left (Exit transition)
fun slideOutToLeft(): ExitTransition =
    slideOutHorizontally(
        targetOffsetX = { -1000 }, // Slide out to the left
        animationSpec = tween(700) // Duration of the transition
    )

// Slide from the left (Pop Enter transition)
fun slideInFromLeft(): EnterTransition =
    slideInHorizontally(
        initialOffsetX = { -1000 }, // Slide from the left
        animationSpec = tween(700) // Duration of the transition
    )

// Slide out to the right (Pop Exit transition)
fun slideOutToRight(): ExitTransition =
    slideOutHorizontally(
        targetOffsetX = { 1000 }, // Slide out to the right
        animationSpec = tween(700) // Duration of the transition
    )