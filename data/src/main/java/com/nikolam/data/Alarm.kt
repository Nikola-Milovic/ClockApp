package com.nikolam.data

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.nikolam.feature_alarm_timeout.AlarmTimeoutActivity


class Alarm : BroadcastReceiver() {

    @SuppressLint("InvalidWakeLockTag")
    override fun onReceive(context: Context, intent: Intent?) {
    //    val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    //    val wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "")
      //  wakeLock.acquire(10*60*1000L /*10 minutes*/)

        val i = Intent(context, AlarmTimeoutActivity::class.java)
       // i.setClassName("com.nikolam", "com.nikolam.feature_alarm_timeout.AlarmTimeoutActivity")

        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(i)
        // Put here YOUR code.
        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show() // For example
       // wakeLock.release()
    }
}