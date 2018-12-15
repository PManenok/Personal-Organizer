package com.gmail.pmanenok.domain.repositories

import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.entity.NoteSearch
import io.reactivex.Completable
import io.reactivex.Flowable

interface NoteRepository : BaseRepository {
    fun getAll(): Flowable<List<Note>>
    fun getById(id: String): Flowable<Note>
    fun getByDay(id: Long): Flowable<List<Note>>
    fun getByDayRange(idBegin: Long, idEnd: Long): Flowable<List<Pair<Long, List<Note>>>>
    fun search(noteSearch: NoteSearch): Flowable<List<Note>>
    fun update(note: Note): Completable
    fun add(note: Note): Completable
    fun delete(id: String): Completable
    fun getAllTypedNoteByRange(dayFirst: Long, dayLast: Long): Flowable<Map<Long, List<Int>>>
    fun getAllTypedNote(): Flowable<Map<Long, List<Int>>>
}