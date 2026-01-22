package com.example.pregnancytracker.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.pregnancytracker.notification.NotificationHelper

class VitalsAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("VitalsAlarmReceiver", "Alarm triggered")
        NotificationHelper.showVitalsReminder(context)
    }
}
