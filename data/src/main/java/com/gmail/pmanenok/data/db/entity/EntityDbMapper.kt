package com.gmail.pmanenok.data.db.entity

import com.gmail.pmanenok.data.db.entity.db.BirthdayDb
import com.gmail.pmanenok.data.db.entity.db.ListDb
import com.gmail.pmanenok.data.db.entity.db.NoteDb
import com.gmail.pmanenok.data.db.entity.db.RecordDb
import com.gmail.pmanenok.domain.entity.*
import com.gmail.pmanenok.domain.entity.List


fun RecordDb.toRecord(): Record {
    return Record(
        id = this.id,
        date = this.day,
        type = NoteType.valueOf(this.type),
        title = this.title,
        comment = this.comment
    )
}

fun NoteRecord.toNote(): Note {
    return Note(
        id = this.id,
        date = this.day,
        type = NoteType.valueOf(this.type),
        title = this.title,
        comment = this.comment,
        textNote = this.textNote
    )
}

fun ListRecord.toList(): List {
    return List(
        id = this.id,
        date = this.day,
        type = NoteType.valueOf(this.type),
        title = this.title,
        comment = this.comment,
        list = this.list.split(", ")
    )
}

fun BirthdayRecord.toBirthday(): Birthday {
    return Birthday(
        id = this.id,
        date = this.day,
        type = NoteType.valueOf(this.type),
        title = this.title,
        comment = this.comment,
        name = this.name,
        birthDate = this.birthDate
    )
}

fun Record.toRecordDb(): RecordDb {
    return RecordDb(
        id = this.id,
        day = this.date,
        type = this.type.name,
        title = this.title,
        comment = this.comment
    )
}

fun Note.toNoteDb(): NoteDb {
    return NoteDb(
        id = this.id,
        textNote = this.textNote
    )
}

fun List.toListDb(): ListDb {
    return ListDb(
        id = this.id,
        list = this.list.joinToString()
    )
}

fun Birthday.toBirthdayDb(): BirthdayDb {
    return BirthdayDb(
        id = this.id,
        name = this.name,
        birthDate = this.birthDate
    )
}


