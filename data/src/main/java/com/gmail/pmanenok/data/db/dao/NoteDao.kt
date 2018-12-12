package com.gmail.pmanenok.data.db.dao

import android.arch.persistence.room.*
import com.gmail.pmanenok.data.db.entity.NoteDb
import com.gmail.pmanenok.data.db.entity.TypedNoteDb
import io.reactivex.Flowable

@Dao
interface NoteDao {
    @Insert
    fun insert(notes: List<NoteDb>)

    @Update
    fun update(note: NoteDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteDb)

    @Delete
    fun delete(note: NoteDb)

    @Query("DELETE FROM note WHERE id = :id")
    fun deleteById(id: String)

    @Query("DELETE FROM note")
    fun deleteAll()

    @Query("SELECT * FROM note")
    fun getAll(): Flowable<List<NoteDb>>

    @Query("SELECT * FROM note WHERE id = :id LIMIT 1")
    fun getById(id: String): Flowable<NoteDb>

    @Query("SELECT * FROM note WHERE day = :day")
    fun getByDay(day: Long): Flowable<NoteDb>

    @Query("SELECT * FROM note WHERE day >= :dayFirst AND day <= :dayLast")
    fun getByDayRange(dayFirst: Long, dayLast: Long): Flowable<NoteDb>

    @Query("SELECT * FROM note WHERE day >= :day")
    fun getAllAfterDay(day: Long): Flowable<NoteDb>

    @Query("SELECT * FROM note WHERE day <= :day")
    fun getAllBeforeDay(day: Long): Flowable<NoteDb>

    @Query("SELECT type, day FROM note")
    fun getAllNoteType(): Flowable<TypedNoteDb>

    @Query("SELECT type, day FROM note WHERE day >= :dayFirst AND day <= :dayLast")
    fun getNoteTypeByDayRange(dayFirst: Long, dayLast: Long): Flowable<TypedNoteDb>
}