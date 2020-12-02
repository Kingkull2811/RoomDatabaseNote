package com.example.mynote.Room

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val getAllListNote:LiveData<List<Note>> = noteDao.getAllListNote()
    val getAllListNoteLowToHigh:LiveData<List<Note>> = noteDao.getAllListNoteLowToHigh()
    val getAllListNoteHighToLow:LiveData<List<Note>> = noteDao.getAllListNoteHighToLow()
    suspend fun addNote(note: Note){
        noteDao.addNote(note);
    }
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
    suspend fun deleteAllList(){
        noteDao.deleteAllList()
    }

}