package com.example.simple_age_calculator.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SaveDataDao {
    @Upsert
    suspend fun UpsertSaveData(saveData: SaveData)

    @Delete
    suspend fun DeleteSavedData(saveData: SaveData)

    @Query("SELECT * FROM savedata ORDER BY dateAdded")
    fun getOrderByDateAdded(): Flow<List<SaveData>>
    @Query("SELECT * FROM savedata ORDER BY name ASC")
    fun getOrderByNameAdded(): Flow<List<SaveData>>
}