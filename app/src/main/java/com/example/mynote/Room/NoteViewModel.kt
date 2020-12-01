package com.example.mynote.Room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {
    private val getAllListNote:LiveData<List<Note>>
    private val repository:NoteRepository
    init {
        val noteDao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(noteDao)
        getAllListNote = repository.getAllListNote
    }

    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }
}