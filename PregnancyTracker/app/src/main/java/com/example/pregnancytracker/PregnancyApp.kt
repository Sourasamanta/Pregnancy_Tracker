package com.example.pregnancytracker

import android.app.Application
import androidx.room.Room
import com.example.pregnancytracker.data.local.PregnancyDatabase
import com.example.pregnancytracker.notification.NotificationHelper

class PregnancyApp : Application() {

    lateinit var database: PregnancyDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            PregnancyDatabase::class.java,
            "pregnancy_db"
        ).fallbackToDestructiveMigration().build()

        NotificationHelper.createNotificationChannel(this)
    }
}
