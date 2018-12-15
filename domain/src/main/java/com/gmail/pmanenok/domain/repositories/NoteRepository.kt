package com.gmail.pmanenok.domain.repositories

import com.gmail.pmanenok.domain.entity.Birthday
import com.gmail.pmanenok.domain.entity.Note
import com.gmail.pmanenok.domain.entity.NoteSearch
import com.gmail.pmanenok.domain.entity.Record
import io.reactivex.Completable
import io.reactivex.Flowable

interface NoteRepository : BaseRepository {
    fun getByDayRange(idBegin: Long, idEnd: Long): Flowable<List<Pair<Long, List<String>>>>
    fun getAllTypedNoteByRange(dayFirst: Long, dayLast: Long): Flowable<Map<Long, List<Int>>>
    fun getAllTypedNote(): Flowable<Map<Long, List<Int>>>
    fun getByDay(id: Long): Flowable<List<Record>>

    fun getNoteRecordById(id: String): Flowable<Note>
    fun deleteNoteRecordById(id: String): Completable
    fun addNoteRecord(note: Note): Completable
    fun updateNoteRecordById(note: Note): Completable

    fun getBirthdayRecordById(id: String): Flowable<Birthday>
    fun deleteBirthdayRecordById(id: String): Completable
    fun addBirthdayRecord(birthday: Birthday): Completable
    fun updateBirthdayRecordById(birthday: Birthday): Completable

    fun getListRecordById(id: String): Flowable<com.gmail.pmanenok.domain.entity.List>
    fun deleteListRecordById(id: String): Completable
    fun addListRecord(list: com.gmail.pmanenok.domain.entity.List): Completable
    fun updateListRecordById(list: com.gmail.pmanenok.domain.entity.List): Completable

    /*fun getAll(): Flowable<List<Note>>
    fun getById(id: String): Flowable<Note>
    fun search(noteSearch: NoteSearch): Flowable<List<Note>>
    fun update(note: Note): Completable
    fun add(note: Note): Completable
    fun delete(id: String): Completable
    */
}