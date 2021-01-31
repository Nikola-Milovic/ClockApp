package com.nikolam.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
        tableName = "Alarms"
)
data class AlarmModel(
        @PrimaryKey val id: Long,
        @ColumnInfo(name = "name") val name: String?,
)