package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDAO {

    //get All notes from data base
    @Query("SELECT * FROM NOTE_TBL")
    fun getAllNotes():Flow<List<Note>>

    //get single note from the table based on the ID
    @Query("SELECT * FROM NOTE_TBL WHERE id=:id")
    suspend fun getNotesById(id:String):Note

    //insert single note to table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    //update note from the notes table
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    //Delete ALl Notes from the Table
    @Query("DELETE FROM NOTE_TBL")
    suspend fun deleteAllNotes()

    //delete particular note from the Notes
    @Delete
    suspend fun deleteNote(note: Note)
}