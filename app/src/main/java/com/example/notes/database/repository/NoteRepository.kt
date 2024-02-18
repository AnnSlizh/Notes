package com.example.notes.database.repository

import androidx.lifecycle.LiveData
import com.example.notes.database.dao.NoteDao
import com.example.notes.database.Note

class NoteRepository(private val noteDao: NoteDao) {

    val allData: LiveData<List<Note>> = noteDao.readAllData()

    fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
}