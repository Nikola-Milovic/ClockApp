package com.nikolam.data

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast


class TimeoutService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        Toast.makeText(this.baseContext, "From service", Toast.LENGTH_SHORT).show()

        stopSelf()
        return START_REDELIVER_INTENT
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}