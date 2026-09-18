package com.example.bloom.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bloom.data.UserPreferences
import com.example.bloom.ui.HomeScreen
import com.example.bloom.ui.onboarding.OnBoardingScreen

sealed class Screen(val route: String) {
    data object OnBoarding : Screen("onboarding")
    data object Home : Screen("home")
}

@Composable
fun BloomNavGraph() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val navController = rememberNavController()
    
    val startDestination = if (userPreferences.isOnboardingCompleted) {
        Screen.Home.route
    } else {
        Screen.OnBoarding.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen(
                onFinish = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnBoarding.route) { inclusive = true }
                    }
                },
                onBackToStart = {
                    // Handle if needed
                }
            )
        }
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
    }
}
