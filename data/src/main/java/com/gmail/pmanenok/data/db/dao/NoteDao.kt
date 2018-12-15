package com.gmail.pmanenok.data.db.dao

import android.arch.persistence.room.*
import com.gmail.pmanenok.data.db.entity.*
import com.gmail.pmanenok.data.db.entity.db.BirthdayDb
import com.gmail.pmanenok.data.db.entity.db.ListDb
import com.gmail.pmanenok.data.db.entity.db.NoteDb
import com.gmail.pmanenok.data.db.entity.db.RecordDb
import io.reactivex.Flowable

@Dao
interface NoteDao {

    @Query("SELECT day , title FROM record WHERE day BETWEEN :dayFirst AND :dayLast")
    fun getByDayRange(dayFirst: Long, dayLast: Long): Flowable<List<DayRecordTitleDb>>

    @Query("SELECT type, day FROM record")
    fun getAllNoteType(): Flowable<List<TypedRecordDb>>

    @Query("SELECT type, day FROM record WHERE day >= :dayFirst AND day <= :dayLast")
    fun getNoteTypeByDayRange(dayFirst: Long, dayLast: Long): Flowable<List<TypedRecordDb>>

    @Query("SELECT * FROM record WHERE day = :day")
    fun getByDay(day: Long): Flowable<List<RecordDb>>


    @Query("SELECT record.id, record.type, record.day, record.comment, record.title, note.textNote FROM record, note WHERE record.id = :id AND note.id = :id LIMIT 1")
    fun getNoteById(id: String): Flowable<NoteRecord>

    @Query("SELECT record.id, record.type, record.day, record.comment, record.title, list.list FROM record, list WHERE record.id = :id AND list.id = :id LIMIT 1")
    fun getListById(id: String): Flowable<ListRecord>

    @Query("SELECT record.id, record.type, record.day, record.comment, record.title, birthday.birthDate, birthday.name FROM record, birthday WHERE record.id = :id AND birthday.id = :id LIMIT 1")
    fun getBirthdayById(id: String): Flowable<BirthdayRecord>


    /*
    @Query("SELECT * FROM note")
    fun getAll(): Flowable<List<NoteDb>>

    @Query("SELECT * FROM note WHERE day > :day")
    fun getAllAfterDay(day: Long): Flowable<List<NoteDb>>

    @Query("SELECT * FROM note WHERE day < :day")
    fun getAllBeforeDay(day: Long): Flowable<List<NoteDb>>*/


}