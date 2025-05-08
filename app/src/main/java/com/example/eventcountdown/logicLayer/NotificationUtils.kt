package com.example.eventcountdown.logicLayer

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

object NotificationUtils {
    fun scheduleEventNotification(context: Context, eventId: Int, eventTimeInMillis: Long) {
        val intent = Intent(context, EventNotificationReceiver::class.java).apply {
            putExtra("EVENT_ID", eventId)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context, eventId, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, eventTimeInMillis, pendingIntent)
    }
}
