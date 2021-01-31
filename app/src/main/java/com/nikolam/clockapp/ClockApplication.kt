package com.nikolam.clockapp

import android.app.Application
import com.nikolam.clockapp.di.navigationModule
import com.nikolam.data.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class ClockApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@ClockApplication)
        }

        loadKoinModules(listOf(navigationModule, databaseModule))

        // This will initialise Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(object : DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    super.log(priority, "clock_$tag", message, t)
                }
            })
        }
    }
}