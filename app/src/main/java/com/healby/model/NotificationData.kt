package com.healby.model

import android.content.Context

data class NotificationData(

        val notificationId: Int = 101,

        val channelId: String = "channel_01",

        val channelName: String = "notification channel",

        val ctx: Context,

        val title: String,

        val body: String

)