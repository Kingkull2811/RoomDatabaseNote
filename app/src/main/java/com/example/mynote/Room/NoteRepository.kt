package com.example.mynote.Room

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val getAllListNote:LiveData<List<Note>> = noteDao.getAllListNote()

    suspend fun addNote(note: Note){
        noteDao.addNote(note);
    }


}