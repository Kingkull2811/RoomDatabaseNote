package com.example.mynote.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_data")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title:String,
    val content:String,
    val priority:Int):Serializable
