package com.example.note.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.note.data.Note
import com.example.note.data.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val db: NotesDao): ViewModel() {
    val notes: LiveData<List<Note>>  = db.getNotes()

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            db.deleteNote(note)
        }
    }
    fun updateNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            db.updateNote()
        }
    }

}

class ViewModelFactory(private val db: NotesDao): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(
            db = db
        )as T
    }
}