package com.healby.utilities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.healby.R
import com.healby.model.NotificationData

class Notification {

    companion object {

        fun pushNotification(notificationData: NotificationData) {
            val mNotificationManager: NotificationManager = notificationData.ctx
                    .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


            val builder = NotificationCompat.Builder(notificationData.ctx, notificationData.channelId).apply {
                setContentTitle(notificationData.title)
                setContentText(notificationData.body)
                setSmallIcon(R.drawable.healby_logo)
                priority = NotificationCompat.PRIORITY_HIGH
                setAutoCancel(false)
                setOngoing(false)
                setStyle(NotificationCompat.BigTextStyle())
            }


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(notificationData.channelId, notificationData.channelName, NotificationManager.IMPORTANCE_HIGH)

                builder.setChannelId(notificationData.channelId)

                mNotificationManager.createNotificationChannel(channel)
            }

            val notification = builder.build()

            mNotificationManager.notify(notificationData.notificationId, notification)
        }

    }

}