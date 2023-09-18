package com.example.lorumblok.ui.standings

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.lorumblok.CHANNEL_ID
import com.example.lorumblok.R
import kotlin.random.Random

class NotificationService(private val context:Context) {
    private val notificationManager=context.getSystemService(NotificationManager::class.java)
    fun showBasicNotification(){
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Nova igra")
            .setContentText("Nova lorum igra je pokrenuta")
            .setSmallIcon(R.drawable.baseline_add_24)
            .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
            .build()
        notificationManager.notify(Random.nextInt(),notification)
    }
}