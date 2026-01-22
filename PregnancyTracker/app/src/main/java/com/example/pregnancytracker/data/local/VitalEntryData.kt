package com.example.pregnancytracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vitals")
data class VitalEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val heartRate: Int,      // Heart Rate (BPM)
    val sys: Int,            // Systolic BP
    val dia: Int,            // Diastolic BP
    val weightKg: Int,       // Weight in kg
    val kicks: Int,          // Baby kicks count
    val dateTimeLabel: String,
    val timestamp: Long = System.currentTimeMillis()
)
