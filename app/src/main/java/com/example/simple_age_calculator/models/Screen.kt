package com.example.simple_age_calculator.models

sealed class Screen(val route: String) {
    data object Home: Screen(route = "home")
    data object Result: Screen(route = "result")
}