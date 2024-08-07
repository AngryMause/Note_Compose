package com.jindoblu.voicenote.activity.mainscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jindoblu.voicenote.data.local.room.NoteDao
import com.jindoblu.voicenote.data.model.room.NoteModel
import com.jindoblu.voicenote.services.VoiceToTexServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val voiceToTexServices: VoiceToTexServices,
    private val noteDao: NoteDao
) : ViewModel() {

    private val _speechText = MutableStateFlow("")
    val speechText = _speechText.asStateFlow()

    fun startVoiceInput() {
        voiceToTexServices.startVoiceInput {
            _speechText.value = it
        }
    }

    fun saveNote(noteModel: NoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e("Test", "init notes: $noteModel")
            noteDao.insertAll(noteModel)
        }
    }

    fun destroyVoiceInput() {
        voiceToTexServices.onDestroy()
    }
}