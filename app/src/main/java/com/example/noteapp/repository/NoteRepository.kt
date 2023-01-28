package com.example.noteapp.repository

import com.example.noteapp.data.NoteDatabaseDAO
import com.example.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDAO: NoteDatabaseDAO) {
    suspend fun addNote(note: Note) = noteDatabaseDAO.addNote(note = note)
    suspend fun deleteNote(note: Note) = noteDatabaseDAO.deleteNote(note)
    suspend fun deleteAllNotes() = noteDatabaseDAO.deleteAllNotes()
    suspend fun updateNote(note: Note) = noteDatabaseDAO.updateNote(note)
    suspend fun getNoteById(id: String) = noteDatabaseDAO.getNotesById(id)
    fun getAllNotes(): Flow<List<Note>> =
        noteDatabaseDAO.getAllNotes().flowOn(Dispatchers.IO).conflate()
}