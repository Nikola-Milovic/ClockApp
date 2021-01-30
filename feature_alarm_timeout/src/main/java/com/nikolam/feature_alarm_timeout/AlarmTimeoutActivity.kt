package com.nikolam.feature_alarm_timeout

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import com.nikolam.data.R

class AlarmTimeoutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_timeout)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}