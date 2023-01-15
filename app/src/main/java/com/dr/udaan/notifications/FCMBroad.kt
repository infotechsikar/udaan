package com.dr.udaan.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.os.Vibrator
import androidx.core.app.NotificationCompat
import com.dr.udaan.MyApp
import com.dr.udaan.R
import com.dr.udaan.room.NotificationEntity
import com.dr.udaan.ui.activities.SplashActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.URL
import java.util.*

class FCMBroad : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {

        val data = message.data
        val title = data["title"].toString()
        val body = data["body"].toString()
        val imageUrl = data["image_url"].toString()

        CoroutineScope(IO)
            .launch {
                MyApp.myDatabase?.notificationDao()?.insert(
                    NotificationEntity(
                        0,
                        imageUrl,
                        title,
                        body,
                        message.sentTime
                    )
                )
            }

        showNotification(title, body, imageUrl)

        super.onMessageReceived(message)
    }

    private fun showNotification(title: String, body: String, imageUrl: String) {

        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(applicationContext, notification)
        r.play()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            r.isLooping = false
        }

        // vibration

        // vibration
        val v = getSystemService(VIBRATOR_SERVICE) as Vibrator
        val pattern = longArrayOf(100, 300, 300, 300)
        v.vibrate(pattern, -1)

        val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
        builder.setSmallIcon(R.drawable.user)

        val resultIntent = Intent(this, SplashActivity::class.java)
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_MUTABLE)
        else
            PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        var img : Bitmap? = null
        try {
            val url = URL(imageUrl)
            img = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: IOException) {
            e.printStackTrace()
        }

        builder.setContentTitle(title)
        builder.setContentText(body)
        builder.setContentIntent(pendingIntent)
        if (img != null) {
            builder.setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(img)
                .bigLargeIcon(null)
            )
        } else {
            builder.setStyle(NotificationCompat
                .BigTextStyle().bigText(body)
            )
        }
        builder.setAutoCancel(true)

        builder.priority = if (Build.VERSION.SDK_INT <= 24) Notification.PRIORITY_MAX else NotificationManager.IMPORTANCE_MAX

        val mNotificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "id_4324"
            val channel = NotificationChannel(
                channelId,
                "Local-Guider",
                NotificationManager.IMPORTANCE_HIGH
            )
            mNotificationManager.createNotificationChannel(channel)
            builder.setChannelId(channelId)
        }

        mNotificationManager.notify(Random().nextInt(1000), builder.build())

    }

}