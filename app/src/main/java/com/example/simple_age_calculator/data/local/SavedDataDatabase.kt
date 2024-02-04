package com.example.simple_age_calculator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SaveData::class],
    version = 1
)
abstract class SaveDataDatabase: RoomDatabase() {
    abstract val saveDataDao: SaveDataDao
}