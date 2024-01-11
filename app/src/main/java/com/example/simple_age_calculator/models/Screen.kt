package com.example.simple_age_calculator.models

sealed class Screen(val route: String) {
    data object Home: Screen(route = "home")
    data object Result: Screen(route =
    "result/{dob}/{todayDate}/{ageYears}/{ageMonths}/{ageDays}/{bornOn}/{totalDays}/{totalWeeks}/{totalMonths}"
    ){
        fun passAgeData(
            dob: String,
            todayDate: String,
            ageYears: Int,
            ageMonths: Int,
            ageDays: Int,
            bornOn : String,
            totalDays: Long,
            totalWeeks: Long,
            totalMonths: Long,
        ):String{
            return "result/$dob/$todayDate/$ageYears/$ageMonths/$ageDays/$bornOn/$totalDays/$totalWeeks/$totalMonths"
        }
    }
}