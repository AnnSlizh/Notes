package com.example.notes.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notes.database.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNote(note: Note)

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun readAllData():LiveData<List<Note>>

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}