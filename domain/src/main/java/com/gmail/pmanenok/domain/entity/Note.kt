package com.gmail.pmanenok.domain.entity

class Note(
    id: String,
    date: Long,
    type: NoteType,
    title: String,
    comment: String,
    val textNote: String
) : Record(id, date, type, title, comment)