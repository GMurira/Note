package com.example.note.data

import android.app.Application
import android.content.Intent
import androidx.room.Room
import java.net.URI

class NotesApp: Application() {
    private var db: NotesDatabase ? = null

    init {
        instance = this
    }
    /**
     * Fun to retrive the database
     */
    private fun getDb(): NotesDatabase{
        if (db != null){
            return db!!
        }else{
            db = Room.databaseBuilder(
                instance!!.applicationContext,
                NotesDatabase::class.java, "Notes"
            ).fallbackToDestructiveMigration().build()

            return db!!
        }
    }
    //To hold instace
    companion object{
        private var instance: NotesApp? = null

        fun getDao(): NotesDao{
            return instance!!.getDb().notesDao()
        }
        fun getUriPermission(uri: Uri){
            instance!!.applicationContext.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }
}