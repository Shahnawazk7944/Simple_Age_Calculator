package com.example.simple_age_calculator.models.SavedViewModal

import com.example.simple_age_calculator.data.local.SaveData

sealed interface SaveDataEvent {
    object SortSavedData: SaveDataEvent

    data class DeleteSavedData( val savedData: com.example.simple_age_calculator.data.local.SaveData):SaveDataEvent

    data class SaveData(
        val name: String,
        val dob: String,
        val todayDate: String,
        val ageYears: String,
        val ageMonths: String,
        val ageDays: String,
        val bornOn: String,
        val totalDays: String,
        val totalWeeks: String,
        val totalMonths: String,
    ): SaveDataEvent
}