package com.gmail.pmanenok.domain.entity

data class TypedNote(
    val id: String,
    val date: Long,
    val type: String
) : DomainEntity