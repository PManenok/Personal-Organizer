package com.gmail.pmanenok.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.gmail.pmanenok.data.db.dao.NoteDao
import com.gmail.pmanenok.data.db.dao.NoteDaoTransactions
import com.gmail.pmanenok.data.db.entity.db.BirthdayDb
import com.gmail.pmanenok.data.db.entity.db.ListDb
import com.gmail.pmanenok.data.db.entity.db.NoteDb
import com.gmail.pmanenok.data.db.entity.db.RecordDb

@Database(entities = [RecordDb::class, NoteDb::class, ListDb::class, BirthdayDb::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "oEaOtdn"

        fun getInstance(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context, AppDataBase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun getNoteDao(): NoteDao
    abstract fun getNoteDaoTransactions(): NoteDaoTransactions
}