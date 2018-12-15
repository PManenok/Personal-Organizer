package com.gmail.pmanenok.data.db.entity.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "list")
data class ListDb(
    @PrimaryKey
    val id: String,
    val list: String
)