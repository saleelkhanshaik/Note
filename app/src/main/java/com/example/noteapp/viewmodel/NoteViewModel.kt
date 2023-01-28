package com.example.noteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.noteapp.data.NoteData
import com.example.noteapp.model.Note


class NoteViewModel : ViewModel() {

    private var notesList = mutableStateListOf<Note>()

    init {
        notesList.addAll(NoteData().getNotesList())
    }

    fun addNote(note: Note){
        notesList.add(note)
    }

    fun removeNote(note: Note){
        notesList.remove(note)
    }

    fun getAllNotes():List<Note> {
        return notesList
    }
}