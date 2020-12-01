package com.example.mynote.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)
    @Query("select * from note_data order by id asc")
    fun getAllListNote():LiveData<List<Note>>

}