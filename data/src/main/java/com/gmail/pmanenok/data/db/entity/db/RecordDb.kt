package com.gmail.pmanenok.data.db.entity.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "record")
data class RecordDb(
    @PrimaryKey
    val id: String,
    val day: Long,
    val type: String,
    val title: String,
    val comment: String
)