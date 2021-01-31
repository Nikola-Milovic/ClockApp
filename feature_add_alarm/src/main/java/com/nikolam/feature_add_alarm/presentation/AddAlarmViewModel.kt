package com.nikolam.feature_add_alarm.presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.nikolam.data.Alarm
import timber.log.Timber

class AddAlarmViewModel : ViewModel() {

    fun setAlarm(context: Context, triggerAtMillis: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
       // val intent = Intent("com.nikolam.START_ALARM");
        val intent = Intent(context, Alarm::class.java);
        intent.putExtra("triggerAtMillis", triggerAtMillis)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                pendingIntent
        ) // Millisec * Second * Minute
    }

    fun cancelAlarm(context: Context, triggerAtMillis: Long) {
        val intent = Intent(context, Alarm::class.java)
        val sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
      //  intent.putExtra("triggerAtMillis", triggerAtMillis)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(sender)

        try {
            alarmManager.cancel(sender)
            Timber.d("Cancelling all pending intents")
        } catch (e: Exception) {
            Timber.e("AlarmManager update was not canceled. $e")
        }
    }
}