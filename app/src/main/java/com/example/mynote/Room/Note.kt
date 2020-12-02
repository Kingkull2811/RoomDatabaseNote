package com.example.mynote.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_data")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title:String,
    var content:String,
    var priority:Int):Serializable
