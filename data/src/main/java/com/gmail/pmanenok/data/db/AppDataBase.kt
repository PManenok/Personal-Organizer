package com.gmail.pmanenok.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.gmail.pmanenok.data.db.dao.NoteDao
import com.gmail.pmanenok.data.db.entity.NoteDb

@Database(entities = [NoteDb::class], version = 1)
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
}
/*
*warning: Schema export directory is not provided to the annotation processor so we cannot export the schema.
* You can either provide `room.schemaLocation` annotation processor argument OR set exportSchema to false.
public abstract class AppDataBase extends android.arch.persistence.room.RoomDatabase {
                ^	*/