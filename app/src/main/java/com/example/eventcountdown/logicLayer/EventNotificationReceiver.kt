package com.example.eventcountdown.logicLayer

import android.Manifest
import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class EventNotificationReceiver : BroadcastReceiver() {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(context: Context, intent: Intent) {
        val notification = NotificationCompat.Builder(context, "event_notifications")
            .setSmallIcon(R.drawable.ic_dialog_info)
            .setContentTitle("Event Started!")
            .setContentText("One of your events has just started.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        NotificationManagerCompat.from(context).notify(1001, notification)
    }
}