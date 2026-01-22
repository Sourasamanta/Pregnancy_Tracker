package com.example.pregnancytracker.ui_ViewModel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pregnancytracker.data.local.VitalEntry
import com.example.pregnancytracker.data.repository.VitalsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class PregnancyVitalsViewModel(
    private val repository: VitalsRepository
) : ViewModel() {

    private val _vitals = MutableStateFlow<List<VitalEntry>>(emptyList())
    val vitals: StateFlow<List<VitalEntry>> = _vitals.asStateFlow()

    private val _showAddDialog = MutableStateFlow(false)
    val showAddDialog: StateFlow<Boolean> = _showAddDialog.asStateFlow()

    init {
        viewModelScope.launch {
            repository.vitals.collect { vitalsList ->
                _vitals.value = vitalsList
            }
        }
    }

    fun openAddDialog() {
        _showAddDialog.value = true
    }

    fun closeAddDialog() {
        _showAddDialog.value = false
    }

    fun addVitals(
        heartRate: Int,
        sys: Int,
        dia: Int,
        weight: Int,
        kicks: Int
    ) {
        viewModelScope.launch {
            val newEntry = VitalEntry(
                id = 0,
                heartRate = heartRate,
                sys = sys,
                dia = dia,
                weightKg = weight,
                kicks = kicks,
                dateTimeLabel = nowLabel(),
                timestamp = System.currentTimeMillis()
            )

            repository.add(newEntry)
            _showAddDialog.value = false
        }
    }

    private fun nowLabel(): String {
        return try {
            val dt = LocalDateTime.now()
            val fmt = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a", Locale.ENGLISH)
            dt.format(fmt).replace("AM", "am").replace("PM", "pm")
        } catch (_: Throwable) {
            "Today"
        }
    }
}

class PregnancyVitalsViewModelFactory(
    private val repository: VitalsRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PregnancyVitalsViewModel::class.java)) {
            return PregnancyVitalsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}