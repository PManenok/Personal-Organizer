package com.gmail.pmanenok.domain.entity

import kotlin.collections.List

class List(
    id: String,
    date: Long,
    type: NoteType,
    title: String,
    val list: List<String>
) : BaseNote(id, date, type, title)