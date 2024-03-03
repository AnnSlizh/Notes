package com.example.notes.database.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.database.HistoryNote
import com.example.notes.database.NotesDatabase
import com.example.notes.database.repository.HistoryNoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryNoteViewModel(application: Application) : AndroidViewModel(application) {

    val allData: LiveData<List<HistoryNote>>
    private val repository: HistoryNoteRepository

    init {
        val historyNoteDao = NotesDatabase.getDatabase(application).historyNoteDao()
        repository = HistoryNoteRepository(historyNoteDao)
        allData = repository.allData
    }

    fun addNote(note: HistoryNote) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun deleteNote(note: HistoryNote) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun getNote(currentNoteId: Int): LiveData<List<HistoryNote>> {
        return repository.getNote(currentNoteId)
    }
}