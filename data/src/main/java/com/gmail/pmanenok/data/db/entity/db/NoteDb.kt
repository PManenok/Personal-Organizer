package com.gmail.pmanenok.data.db.entity.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "note")
data class NoteDb(
    @PrimaryKey
    val id: String,
    val textNote: String
)