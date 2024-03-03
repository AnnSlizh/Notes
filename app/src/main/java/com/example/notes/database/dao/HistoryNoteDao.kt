package com.example.notes.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notes.database.HistoryNote
import com.example.notes.database.Note
import java.util.Date

@Dao
interface HistoryNoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNote(note: HistoryNote)

    @Query("SELECT * FROM historyNote ORDER BY id ASC")
    fun readAllData(): LiveData<List<HistoryNote>>

    @Query("SELECT * FROM historyNote WHERE historyNote.currentNoteId = :currentNoteId")
    fun getNote(currentNoteId: Int): LiveData<List<HistoryNote>>

    @Delete
    fun deleteNote(note: HistoryNote)
}