package com.example.simple_age_calculator.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
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
    val dateAdded: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
