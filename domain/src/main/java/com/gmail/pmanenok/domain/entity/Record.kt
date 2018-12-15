package com.gmail.pmanenok.domain.entity

open class Record(
    val id: String,
    val date: Long,
    val type: NoteType,
    val title: String,
    val comment: String
) : DomainEntity