package com.example.pregnancytracker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalEntryDao {
    @Query("SELECT * FROM vitals ORDER BY timestamp DESC")
    fun observeAllVitals(): Flow<List<VitalEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: VitalEntry)

    @Query("SELECT COUNT(*) FROM vitals")
    suspend fun getCount(): Int
}
