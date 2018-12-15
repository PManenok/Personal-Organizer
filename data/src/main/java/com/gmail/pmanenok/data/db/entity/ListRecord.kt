package com.gmail.pmanenok.data.db.entity

import android.arch.persistence.room.ColumnInfo
import kotlin.collections.List

class ListRecord(
    val id: String,
    val day: Long,
    val type: String,
    val title: String,
    val comment: String,
    //@ColumnInfo(name = "list_entries")
    val list: String
)