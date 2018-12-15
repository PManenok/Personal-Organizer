package com.gmail.pmanenok.data.db.entity

import android.arch.persistence.room.ColumnInfo


data class BirthdayRecord(
    val id: String,
    val day: Long,
    val type: String,
    val title: String,
    val comment: String,
    @ColumnInfo(name = "note_text")
    val name: String,
    @ColumnInfo(name = "note_text")
    val birthDate: Long
)