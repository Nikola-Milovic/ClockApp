package com.nikolam.feature_add_alarm.presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.data.Alarm
import com.nikolam.data.db.AlarmDatabase
import com.nikolam.data.db.models.AlarmModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class AddAlarmViewModel(
        private val db: AlarmDatabase
) : ViewModel() {

    fun setAlarm(context: Context, triggerAtMillis: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
       // val intent = Intent("com.nikolam.START_ALARM");
        val intent = Intent(context, Alarm::class.java);


        val triggerAtTest = triggerAtMillis // System.currentTimeMillis() + 10000L



        val r = Random()
        val reqCode: Int = r.nextInt(10000)

        intent.putExtra("triggerAtMillis", triggerAtTest)
        intent.putExtra("reqCode", reqCode)

        viewModelScope.launch {
            db.alarmDao().addAlarm(AlarmModel(triggerAtTest, "name", reqCode))
        }

        val pendingIntent = PendingIntent.getBroadcast(context, reqCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerAtTest,
                pendingIntent
        ) // Millisec * Second * Minute
    }
}