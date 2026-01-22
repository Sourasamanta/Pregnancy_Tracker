package com.example.pregnancytracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [VitalEntry::class],
    version = 2,
    exportSchema = false
)
abstract class PregnancyDatabase : RoomDatabase() {
    abstract fun vitalEntryDao(): VitalEntryDao
}
