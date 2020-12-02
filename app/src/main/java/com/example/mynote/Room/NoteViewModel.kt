package com.example.mynote.Room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {
    private val getAllListNote:LiveData<List<Note>>
    private val getALlListLowToHigh:LiveData<List<Note>>
    private val getALlListHighToLow:LiveData<List<Note>>
    private val repository:NoteRepository
    init {
        val noteDao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(noteDao)
        getAllListNote = repository.getAllListNote
        getALlListLowToHigh = repository.getAllListNoteLowToHigh
        getALlListHighToLow = repository.getAllListNoteHighToLow
    }

    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }
    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }
    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }
    fun deleteAllList(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllList()
        }
    }
}