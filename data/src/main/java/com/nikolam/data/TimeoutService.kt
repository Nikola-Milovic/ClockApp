package com.nikolam.data

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.nikolam.data.db.AlarmDatabase
import com.nikolam.data.di.provideDB
import kotlinx.coroutines.*
import timber.log.Timber


class TimeoutService : Service() {

    private lateinit var db : AlarmDatabase

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    override fun onCreate() {
        super.onCreate()
        db = provideDB(baseContext)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        Timber.d("triggers at from service %s", intent?.getLongExtra("triggerAtMillis", 0).toString())

        val triggerAtMillis =  intent!!.getLongExtra("triggerAtMillis", 0)

        serviceScope.launch {
            withContext(Dispatchers.IO) { db.alarmDao().deleteAlarm(triggerAtMillis) }
            Timber.d("Stop self")
            stopSelf()
        }


        return START_REDELIVER_INTENT
    }


    override fun onDestroy() {
        serviceScope.coroutineContext.cancelChildren()
        Timber.d("On destroy")
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}