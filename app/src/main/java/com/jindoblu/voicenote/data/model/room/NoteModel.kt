package com.jindoblu.voicenote.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "note_name") val noteName: String,
    @ColumnInfo(name = "note_description") val note: String
)
