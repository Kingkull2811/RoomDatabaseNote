package com.example.mynote.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)
    @Update
    suspend fun updateNote(note: Note)
    @Delete
    suspend fun deleteNote(note:Note)
    @Query("select * from note_data order by id asc")
    fun getAllListNote():LiveData<List<Note>>
    @Query("select * from note_data order by priority asc")
    fun getAllListNoteLowToHigh():LiveData<List<Note>>
    @Query("select *from note_data order by priority desc")
    fun getAllListNoteHighToLow():LiveData<List<Note>>
    @Query("delete from note_data")
    suspend fun deleteAllList()
}