package com.example.simple_age_calculator.models

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.simple_age_calculator.CalculateAge
import com.example.simple_age_calculator.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(onNavigateToResult = {
                navController.navigate(
                    Screen.Result.route
                )
            })
        }

        composable(route = Screen.Result.route) {
            CalculateAge(onNavigateToHome = {
                navController.popBackStack()
            })
        }
    }
}
