package com.example.note.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [NotesApp::class])
abstract class NotesDatabase: RoomDatabase() {
   abstract fun notesDao(): NotesDao
}