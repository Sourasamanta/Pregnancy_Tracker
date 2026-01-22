package com.example.pregnancytracker.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.pregnancytracker.notification.ReminderScheduler

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            ReminderScheduler.scheduleReminder(context)
        }
    }
}
