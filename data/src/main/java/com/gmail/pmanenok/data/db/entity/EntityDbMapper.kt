package com.gmail.pmanenok.data.db.entity

import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.entity.TypedNote

fun Note.transformToDb(): NoteDb {
    return NoteDb(
        id = this.id,
        day = this.date,
        type = this.type.toString(),
        title = this.title,
        comment = this.comment
    )

}


