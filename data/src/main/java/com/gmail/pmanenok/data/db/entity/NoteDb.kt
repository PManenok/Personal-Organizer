package com.gmail.pmanenok.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "note")
data class NoteDb (
    @PrimaryKey
    val id:String,
    val day: Long,
    val type: String,
    val title: String,
    val comment: String
)