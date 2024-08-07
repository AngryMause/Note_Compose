package com.jindoblu.voicenote.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jindoblu.voicenote.data.model.room.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}