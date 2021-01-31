package com.nikolam.clockapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.findNavController
import com.nikolam.clockapp.databinding.ActivityMainBinding
import com.nikolam.common.navigation.NavManager
import org.koin.android.ext.android.inject
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private val navController get() = findNavController(R.id.navHostFragment)

    private lateinit var binding: ActivityMainBinding

    private val navManager: NavManager by inject()

    private fun initNavManager() {
        navManager.setOnNavEvent {
            navController.navigate(it)
        }

        navManager.setPopBackStack{
            navController.popBackStack()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavManager()

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        createNotificationChannel()

        // The window will not be resized when virtual keyboard is shown (bottom navigation bar will be
        // hidden under virtual keyboard)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        Timber.v("onCreate ${javaClass.simpleName}")
    }


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Nikola Alarm"
            val descriptionText = "TestDescription"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("nalarm", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}