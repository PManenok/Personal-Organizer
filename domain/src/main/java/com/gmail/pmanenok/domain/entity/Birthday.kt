package com.gmail.pmanenok.domain.entity

class Birthday(
    id: String,
    date: Long,
    type: NoteType,
    title: String,
    val name: String,
    val birthDate: Long
) : BaseNote(id, date, type, title)