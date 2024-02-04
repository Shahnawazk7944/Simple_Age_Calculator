package com.example.simple_age_calculator.models.SavedViewModal

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simple_age_calculator.data.local.SaveData
import com.example.simple_age_calculator.data.local.SaveDataDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SaveDataViewModal(private val saveDataDao: SaveDataDao) :
    ViewModel() {
    private val isSortedByDateAdded = MutableStateFlow(true)

    private var saveDatas = isSortedByDateAdded.flatMapLatest { sort ->
        if (sort) {
            saveDataDao.getOrderByDateAdded()
        } else {
            saveDataDao.getOrderByNameAdded()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(SaveDataState())
    val state = combine(
        _state, isSortedByDateAdded, saveDatas
    ) { state, isSortedByDateAdded, saveDatas ->
        state.copy(
            savedData = saveDatas
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SaveDataState())

    fun onEvent(event: SaveDataEvent) {
        when (event) {
            is SaveDataEvent.DeleteSavedData -> {
                viewModelScope.launch {
                    saveDataDao.DeleteSavedData(event.savedData)
                }

            }

            is SaveDataEvent.SaveData -> {
                val datas = SaveData(
                    name = state.value.name.value,
                    dob = state.value.dob.value,
                    todayDate = state.value.todayDate.value,
                    ageYears = state.value.ageYears.value,
                    ageMonths = state.value.ageMonths.value,
                    ageDays = state.value.ageDays.value,
                    bornOn = state.value.bornOn.value,
                    totalDays = state.value.totalDays.value,
                    totalWeeks = state.value.totalWeeks.value,
                    totalMonths = state.value.totalMonths.value,
                    dateAdded = System.currentTimeMillis(),
                )
                viewModelScope.launch {
                    saveDataDao.UpsertSaveData(datas)
                }

                _state.update {
                    it.copy(
                        name = mutableStateOf(""),
                        dob = mutableStateOf(""),
                        todayDate = mutableStateOf(""),
                        ageYears = mutableStateOf(""),
                        ageMonths = mutableStateOf(""),
                        ageDays = mutableStateOf(""),
                        bornOn = mutableStateOf(""),
                        totalDays = mutableStateOf(""),
                        totalWeeks = mutableStateOf(""),
                        totalMonths = mutableStateOf(""),
                    )
                }

            }

            SaveDataEvent.SortSavedData -> {
                isSortedByDateAdded.value = !isSortedByDateAdded.value

            }
        }
    }

}