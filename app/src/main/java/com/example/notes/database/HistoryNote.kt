package com.example.notes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "historyNote")
data class HistoryNote (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "currentNoteId") val currentNoteId: Int,
    @ColumnInfo(name = "historyTitle") val title: String,
    @ColumnInfo(name = "historyDescription") val description: String,
    @ColumnInfo(name = "editDate") val date: Date
)
