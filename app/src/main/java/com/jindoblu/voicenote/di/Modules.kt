package com.jindoblu.voicenote.di

import android.content.Context
import androidx.room.Room
import com.jindoblu.voicenote.data.local.room.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Modules {
    @Provides
    fun provideContext(@ApplicationContext context: Context)=context
    @Provides
    fun providesDataBase(context: Context)= Room.databaseBuilder(
        context,
        NoteDatabase::class.java, "NoteDatabase"
    ).build()

    @Provides
    fun providesNoteDao(noteDatabase: NoteDatabase)=noteDatabase.noteDao()
}