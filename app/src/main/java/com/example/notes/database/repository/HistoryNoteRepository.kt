package com.example.notes.database.repository

import androidx.lifecycle.LiveData
import com.example.notes.database.HistoryNote
import com.example.notes.database.dao.HistoryNoteDao

class HistoryNoteRepository(private val historyNoteDao: HistoryNoteDao) {

    val allData: LiveData<List<HistoryNote>> = historyNoteDao.readAllData()

    fun addNote(note: HistoryNote) {
        historyNoteDao.addNote(note)
    }

    fun deleteNote(note: HistoryNote) {
        historyNoteDao.deleteNote(note)
    }

    fun getNote(currentNoteId: Int): LiveData<List<HistoryNote>> {
        return historyNoteDao.getNote(currentNoteId)
    }

}