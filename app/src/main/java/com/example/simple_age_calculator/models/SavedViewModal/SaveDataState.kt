package com.example.simple_age_calculator.models.SavedViewModal

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.simple_age_calculator.data.local.SaveData

data class SaveDataState(
    val savedData: List<SaveData> = emptyList(),
    val name: MutableState<String> = mutableStateOf(""),
    val dob: MutableState<String> = mutableStateOf(""),
    val todayDate: MutableState<String> = mutableStateOf(""),
    val ageYears: MutableState<String> = mutableStateOf(""),
    val ageMonths: MutableState<String> = mutableStateOf(""),
    val ageDays: MutableState<String> = mutableStateOf(""),
    val bornOn: MutableState<String> = mutableStateOf(""),
    val totalDays: MutableState<String> = mutableStateOf(""),
    val totalWeeks: MutableState<String> = mutableStateOf(""),
    val totalMonths: MutableState<String> = mutableStateOf(""),
)
