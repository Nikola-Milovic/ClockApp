package com.nikolam.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikolam.data.db.models.AlarmModel

@Database(entities = [AlarmModel::class], version = 1)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}