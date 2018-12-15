package com.gmail.pmanenok.domain.entity

class Birthday(
    id: String,
    date: Long,
    type: NoteType,
    title: String,
    comment: String,
    val name: String,
    val birthDate: Long
) : Record(id, date, type, title, comment)