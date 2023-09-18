package com.example.lorumblok

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi

const val CHANNEL_ID="lorum_notification"
const val CHANNE_NAME="Lorum"
class LorumBlokApplication:Application() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val notificationChannel= NotificationChannel(
            CHANNEL_ID,
            CHANNE_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}