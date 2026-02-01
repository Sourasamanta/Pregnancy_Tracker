package com.example.pregnancytracker.notification

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class VitalsReminderWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        NotificationHelper.showVitalsReminder(applicationContext)
        return Result.success()
    }
}

object ReminderScheduler {

    private const val WORK_NAME = "vitals_reminder_work"

    fun scheduleReminder(context: Context) {
        val workManager = WorkManager.getInstance(context)

        workManager.cancelUniqueWork(WORK_NAME)

        val periodicWork = PeriodicWorkRequestBuilder<VitalsReminderWorker>(
            5, TimeUnit.HOURS
        )
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                    .build()
            )
            .build()

        workManager.enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            periodicWork
        )
    }
}
