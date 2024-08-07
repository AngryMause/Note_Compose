package com.jindoblu.voicenote.activity.noteScree

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jindoblu.voicenote.data.local.room.NoteDao
import com.jindoblu.voicenote.data.model.room.NoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteDao: NoteDao
) : ViewModel() {

    val noteDaos: Flow<List<NoteModel>> = noteDao.getAll()

    fun deleteNoteById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.deleteNotesById(id)
        }
    }
}