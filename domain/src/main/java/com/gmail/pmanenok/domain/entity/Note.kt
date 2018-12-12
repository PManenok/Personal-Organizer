package com.gmail.pmanenok.domain.entity

data class Note(
    val id: String,
    val date: Long,
    val type: NoteType,
    val title: String,
    val comment: String
) : DomainEntity