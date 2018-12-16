package com.gmail.pmanenok.data.db.dao

import android.arch.persistence.room.*
import com.gmail.pmanenok.data.db.entity.*
import com.gmail.pmanenok.data.db.entity.db.BirthdayDb
import com.gmail.pmanenok.data.db.entity.db.ListDb
import com.gmail.pmanenok.data.db.entity.db.NoteDb
import com.gmail.pmanenok.data.db.entity.db.RecordDb
import io.reactivex.Flowable


@Dao
abstract class NoteDaoTransactions {
    @Insert
    abstract fun insertRecordDb(record: RecordDb)

    @Query("DELETE FROM record WHERE id = :id")
    abstract fun deleteRecordDb(id: String)

    @Update
    abstract fun updateRecordDb(record: RecordDb)

    @Insert
    abstract fun insertNoteDb(note: NoteDb)

    @Query("DELETE FROM note WHERE id = :id")
    abstract fun deleteNoteDb(id: String)

    @Update
    abstract fun updateNoteDb(note: NoteDb)

    @Insert
    abstract fun insertListDb(list: ListDb)

    @Query("DELETE FROM list WHERE id = :id")
    abstract fun deleteListDb(id: String)

    @Update
    abstract fun updateListDb(list: ListDb)

    @Insert
    abstract fun insertBirthdayDb(birthday: BirthdayDb)

    @Query("DELETE FROM birthday WHERE id = :id")
    abstract fun deleteBirthdayDb(id: String)

    @Update
    abstract fun updateBirthdayDb(birthday: BirthdayDb)


    @Transaction
    open fun insertNote(record: RecordDb, note: NoteDb) {
        insertRecordDb(record)
        insertNoteDb(note)
    }

    @Transaction
    open fun insertList(record: RecordDb, list: ListDb) {
        insertRecordDb(record)
        insertListDb(list)
    }

    @Transaction
    open fun insertBirthday(record: RecordDb, birthday: BirthdayDb) {
        insertRecordDb(record)
        insertBirthdayDb(birthday)
    }


    @Transaction
    open fun deleteNote(id: String) {
        deleteRecordDb(id)
        deleteNoteDb(id)
    }

    @Transaction
    open fun deleteList(id: String) {
        deleteRecordDb(id)
        deleteListDb(id)
    }

    @Transaction
    open fun deleteBirthday(id: String) {
        deleteRecordDb(id)
        deleteBirthdayDb(id)
    }


    @Transaction
    open fun updateNote(record: RecordDb, note: NoteDb) {
        updateRecordDb(record)
        updateNoteDb(note)
    }

    @Transaction
    open fun updateList(record: RecordDb, list: ListDb) {
        updateRecordDb(record)
        updateListDb(list)
    }

    @Transaction
    open fun updateBirthday(record: RecordDb, birthday: BirthdayDb) {
        updateRecordDb(record)
        updateBirthdayDb(birthday)
    }
}