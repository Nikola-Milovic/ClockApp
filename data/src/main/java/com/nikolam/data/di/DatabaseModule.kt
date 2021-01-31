package com.nikolam.data.di

import android.content.Context
import androidx.room.Room
import com.nikolam.data.db.AlarmDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { provideDB(get())}
}

fun provideDB(context: Context): AlarmDatabase {
    return Room.databaseBuilder(
            context,
            AlarmDatabase::class.java, "alarm-db"
    ).build()
}