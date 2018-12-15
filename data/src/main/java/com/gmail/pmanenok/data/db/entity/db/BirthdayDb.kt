package com.gmail.pmanenok.data.db.entity.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "birthday")
data class BirthdayDb(
    @PrimaryKey
    val id: String,
    val name: String,
    val birthDate: Long
)