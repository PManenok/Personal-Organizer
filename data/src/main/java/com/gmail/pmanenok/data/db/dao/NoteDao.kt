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
    fun getByDayRange(dayFirst: Long, dayLast: Long): Flowable<List<DayNoteTitleDb>>


    /*@Insert
    fun insert(notes: List<RecordDb>)

    @Update
    fun update(note: RecordDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: RecordDb)
clearAllTables()
    @Delete
    fun delete(note: RecordDb)*/

    /*@Query("DELETE FROM record, note, list, birthday WHERE id = :id")
    fun deleteById(id: String)*/


    /*@Query("DELETE FROM note")
    fun deleteAll()*/

    @Query("SELECT * FROM note")
    fun getAll(): Flowable<List<NoteDb>>

    @Query("SELECT record.id, record.type, record.day, record.comment, record.title, note.textNote AS department_name FROM record, note WHERE note_info_id as :id LIMIT 1")
    fun getNoteById(id: String): Flowable<NoteRecord>

    @Query("SELECT * FROM note WHERE id = :id LIMIT 1")
    fun getListById(id: String): Flowable<ListDb>

    @Query("SELECT * FROM note WHERE id = :id LIMIT 1")
    fun getBirthdayById(id: String): Flowable<BirthdayDb>

    @Query("SELECT * FROM note WHERE day = :day")
    fun getByDay(day: Long): Flowable<List<NoteDb>>

    @Query("SELECT * FROM note WHERE day > :day")
    fun getAllAfterDay(day: Long): Flowable<List<NoteDb>>

    @Query("SELECT * FROM note WHERE day < :day")
    fun getAllBeforeDay(day: Long): Flowable<List<NoteDb>>

    @Query("SELECT type, day FROM note")
    fun getAllNoteType(): Flowable<List<TypedNoteDb>>

    @Query("SELECT type, day FROM note WHERE day >= :dayFirst AND day <= :dayLast")
    fun getNoteTypeByDayRange(dayFirst: Long, dayLast: Long): Flowable<List<TypedNoteDb>>
}