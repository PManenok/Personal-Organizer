package com.gmail.pmanenok.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

data class DayNoteTitleDb (
    val day: Long,
    val title: String
)
