package com.example.mynote.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities= [Note::class],version = 1,exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun getNoteDao():NoteDao
    companion object{
        @Volatile
        private var INSTANCE:NoteDatabase?=null
        fun getDatabase(context:Context):NoteDatabase{
            val database = INSTANCE
            if(database!=null) return database
            else synchronized(this){
                val instance = Room.databaseBuilder(context,NoteDatabase::class.java,"db_note").build();
                INSTANCE = instance
                return instance;
            }
        }
    }
}