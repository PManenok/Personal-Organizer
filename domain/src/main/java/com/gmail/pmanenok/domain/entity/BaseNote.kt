package com.gmail.pmanenok.domain.entity

abstract class BaseNote (
    val id: String,
    val date: Long,
    val type: NoteType,
    val title: String
)  : DomainEntity