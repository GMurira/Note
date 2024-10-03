package com.example.note.data

object Constants {
    const val TABLE_NAME = "notes"


    /**
     * Define a place holder to be displayed if the notes are not retrieved
     */
    val noteDetailPlaceHolder = Note(
        note = "Cannot find note details",
        id = 0,
        title = "Cannot find note details"
    )


    fun noteDetailNavigation(noteId: Int) = "noteDetail/$noteId"
    fun noteEditNavigation(noteId: Int) = "noteEdit/$noteId"
}