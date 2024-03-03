package com.example.notes.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notes.database.dao.HistoryNoteDao
import com.example.notes.database.dao.NoteDao

@Database(entities = [Note::class,HistoryNote::class], version = 3)
@TypeConverters(DateConverter::class)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun historyNoteDao(): HistoryNoteDao

    companion object {
        private const val DATABASE_NAME = "Notes"
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java, DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}