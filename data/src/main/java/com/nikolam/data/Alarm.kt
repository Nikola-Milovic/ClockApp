package com.nikolam.data

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.nikolam.feature_alarm_timeout.AlarmTimeoutActivity


class Alarm : BroadcastReceiver() {

    @SuppressLint("InvalidWakeLockTag")
    override fun onReceive(context: Context, intent: Intent?) {
    //    val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    //    val wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "")
      //  wakeLock.acquire(10*60*1000L /*10 minutes*/)

        val fullScreenIntent = Intent(context, AlarmTimeoutActivity::class.java)
        val fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationBuilder =
                NotificationCompat.Builder(context, "nalarm")
                        .setSmallIcon(R.drawable.ic_baseline_access_alarm_24)
                        .setContentTitle("Alarm!")
                        .setContentText("Alarm timeout")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_ALARM)

                        // Use a full-screen intent only for the highest-priority alerts where you
                        // have an associated activity that you would like to launch after the user
                        // interacts with the notification. Also, if your app targets Android 10
                        // or higher, you need to request the USE_FULL_SCREEN_INTENT permission in
                        // order for the platform to invoke this notification.
                        .setFullScreenIntent(fullScreenPendingIntent, true)

        val notification = notificationBuilder.build()

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(123, notification)
        }

        Toast.makeText(context, "Alarm", Toast.LENGTH_SHORT).show()
       // wakeLock.release()
    }
}