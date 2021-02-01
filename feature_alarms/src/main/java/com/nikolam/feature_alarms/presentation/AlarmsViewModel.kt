package com.nikolam.feature_alarms.presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.common.navigation.NavManager
import com.nikolam.data.Alarm
import com.nikolam.data.db.AlarmDatabase
import com.nikolam.data.db.models.AlarmModel
import com.nikolam.data.di.databaseModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class AlarmsViewModel(
    private val navManager: NavManager,
    private val alarmDatabase: AlarmDatabase
) : ViewModel() {

    fun navigateToNewAlarm() {
        val uri = Uri.parse("clock://addAlarm")
        navManager.navigate(uri)
    }

    fun getAlarms() : LiveData<List<AlarmModel>>{
        return alarmDatabase.alarmDao().getAllAlarms()
    }

    fun deleteAlarm(id : Long, context : Context){
        var reqCode : Int = -1
        viewModelScope.launch {
           withContext(Dispatchers.IO) {
               reqCode = alarmDatabase.alarmDao().getAlarmById(id).requestCode ?: 0
           }
            withContext(Dispatchers.IO) {
                alarmDatabase.alarmDao().deleteAlarm(id)
            }

            withContext(Dispatchers.Main){
                cancelAlarm(context, reqCode)
            }
        }
    }

    private fun cancelAlarm(context: Context, reqCode: Int) {
        if (reqCode == -1) return
        val intent = Intent(context, Alarm::class.java)
        val sender = PendingIntent.getBroadcast(context, reqCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
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