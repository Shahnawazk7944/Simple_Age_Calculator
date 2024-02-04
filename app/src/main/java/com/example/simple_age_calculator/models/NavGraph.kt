package com.example.simple_age_calculator.models

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.simple_age_calculator.CalculateAge
import com.example.simple_age_calculator.HomeScreen
import com.example.simple_age_calculator.SavedDataScreen
import com.example.simple_age_calculator.models.SavedViewModal.SaveDataEvent
import com.example.simple_age_calculator.models.SavedViewModal.SaveDataState

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    state: SaveDataState,
    onEvent: (SaveDataEvent) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.SavedData.route) {
            SavedDataScreen(navController = navController, state = state, onEvent = onEvent )
        }
        composable(
            route = Screen.Result.route,
            arguments = listOf(
                navArgument("dob") { type = NavType.StringType },
                navArgument("todayDate") { type = NavType.StringType },
                navArgument("ageYears") { type = NavType.IntType },
                navArgument("ageMonths") { type = NavType.IntType },
                navArgument("ageDays") { type = NavType.IntType },
                navArgument("bornOn") { type = NavType.StringType },
                navArgument("totalDays") { type = NavType.LongType },
                navArgument("totalWeeks") { type = NavType.LongType },
                navArgument("totalMonths") { type = NavType.LongType },
            )
        ) {

                navBackStackEntry ->
            val dob = navBackStackEntry.arguments?.getString("dob")
            val todayDate = navBackStackEntry.arguments?.getString("todayDate")
            val ageYears = navBackStackEntry.arguments?.getInt("ageYears").toString()
            val ageMonths = navBackStackEntry.arguments?.getInt("ageMonths").toString()
            val ageDays = navBackStackEntry.arguments?.getInt("ageDays").toString()
            val bornOn = navBackStackEntry.arguments?.getString("bornOn")
            val totalDays = navBackStackEntry.arguments?.getLong("totalDays").toString()
            val totalWeeks = navBackStackEntry.arguments?.getLong("totalWeeks").toString()
            val totalMonths = navBackStackEntry.arguments?.getLong("totalMonths").toString()
//            Log.d(
//                "dob :- ",
//                navBackStackEntry.arguments?.getString("dob").toString()
//            )
//            Log.d(
//                "todayDate :- ",
//                navBackStackEntry.arguments?.getString("todayDate").toString()
//            )
//            Log.d(
//                "ageYears :- ",
//                navBackStackEntry.arguments?.getInt("ageYears").toString()
//            )
//            Log.d(
//                "ageMonths :- ",
//                navBackStackEntry.arguments?.getInt("ageMonths").toString()
//            )
//            Log.d(
//                "ageDays:- ",
//                navBackStackEntry.arguments?.getInt("ageDays").toString()
//            )
//            Log.d(
//                "bornOn :- ",
//                navBackStackEntry.arguments?.getString("bornOn").toString()
//            )
//            Log.d(
//                "totalDays :- ",
//                navBackStackEntry.arguments?.getLong("totalDays").toString()
//            )
//            Log.d(
//                "totalWeeks :- ",
//                navBackStackEntry.arguments?.getLong("totalWeeks").toString()
//            )
//            Log.d(
//                "totalMonths :- ",
//                navBackStackEntry.arguments?.getLong("totalMonths").toString()
//            )
            if (dob != null && todayDate != null && ageYears != null && ageMonths != null && ageDays != null && bornOn != null && totalDays != null && totalWeeks != null && totalMonths != null
                ) {
                CalculateAge(
                    navController = navController,
                    dob = dob,
                    todayDate = todayDate,
                    ageYears = ageYears,
                    ageMonths = ageMonths,
                    ageDays = ageDays,
                    bornOn = bornOn,
                    totalDays = totalDays,
                    totalWeeks = totalWeeks,
                    totalMonths = totalMonths,
                    state = state,
                    onEvent = onEvent
                )
            }
        }
    }
}
