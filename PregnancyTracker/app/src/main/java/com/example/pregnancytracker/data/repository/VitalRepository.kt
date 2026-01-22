package com.example.pregnancytracker.data.repository

import com.example.pregnancytracker.data.local.VitalEntry
import com.example.pregnancytracker.data.local.VitalEntryDao
import kotlinx.coroutines.flow.Flow

class VitalsRepository(
    private val dao: VitalEntryDao
) {
    val vitals: Flow<List<VitalEntry>> = dao.observeAllVitals()

    suspend fun add(entry: VitalEntry) = dao.insert(entry)
}