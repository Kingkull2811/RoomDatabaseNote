package com.example.mynote.Room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {
    val getAllListNote:LiveData<List<Note>>
    val getALlListLowToHigh:LiveData<List<Note >>
    val getALlListHighToLow:LiveData<List<Note>>
    val repository:NoteRepository
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