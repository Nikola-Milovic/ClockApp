package com.nikolam.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikolam.data.db.models.AlarmModel

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlarm(alarmModel: AlarmModel)

    @Query("DELETE FROM alarms WHERE id = :alarmID")
    fun deleteAlarm(alarmID: Long)

    @Query("SELECT * FROM alarms")
    fun getAllAlarms() : LiveData<List<AlarmModel>>

    @Query("SELECT * FROM alarms WHERE id = :id")
    fun getAlarmById(id : Long) : AlarmModel
}

