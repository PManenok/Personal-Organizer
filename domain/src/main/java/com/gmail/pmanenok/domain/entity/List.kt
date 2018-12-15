package com.gmail.pmanenok.domain.entity

import kotlin.collections.List

class List(
    id: String,
    date: Long,
    type: NoteType,
    title: String,
    comment: String,
    val list: List<String>
) : Record(id, date, type, title, comment)