package com.example.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteapp.util.DateConverters
import com.example.noteapp.model.Note
import com.example.noteapp.util.UUIDConverter

@Database(
    entities = [Note::class],
    version = 2, exportSchema = false
)
@TypeConverters(DateConverters::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDoa() : NoteDatabaseDAO
}