package com.example.simple_age_calculator

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ShowError(
    snackbarHostState: SnackbarHostState,
    message: String,
    snackBarScop: CoroutineScope,
) {
    snackBarScop.launch {
        snackbarHostState.showSnackbar(
            message = message,
            actionLabel = "Retry",
            duration = SnackbarDuration.Short
        )
    }

}