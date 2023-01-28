package com.example.noteapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.NoteData
import com.example.noteapp.model.Note
import com.example.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    private val TAG: String = "NoteViewModel"
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val notesList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collectLatest { listOfNote ->
                _noteList.value = listOfNote
            }
        }
    }

    fun addNote(note: Note, dispatcherIo: CoroutineDispatcher = Dispatchers.IO) =
        viewModelScope.launch(dispatcherIo) {
            repository.addNote(note)
        }

    fun updateNote(note: Note, dispatcherIo: CoroutineDispatcher = Dispatchers.IO) =
        viewModelScope.launch(dispatcherIo) {
            repository.updateNote(note)
        }

    fun removeNote(note: Note, dispatcherIo: CoroutineDispatcher = Dispatchers.IO) =
        viewModelScope.launch(dispatcherIo) {
            repository.deleteNote(note)
        }

    fun deleteAll(dispatcherIo: CoroutineDispatcher = Dispatchers.IO) =
        viewModelScope.launch(dispatcherIo) {
            repository.deleteAllNotes()
        }

}