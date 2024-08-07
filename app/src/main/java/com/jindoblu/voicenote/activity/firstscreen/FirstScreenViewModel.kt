package com.jindoblu.voicenote.activity.firstscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jindoblu.voicenote.data.local.shared.SharedServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor(
    private val sharedServices: SharedServices
) : ViewModel() {

    private val _isPasswordExist = MutableStateFlow(false)
    val isPasswordExist = _isPasswordExist.asStateFlow()
    private val _isPasswordCorrect = MutableStateFlow<Boolean?>(null)
    val isPasswordCorrect = _isPasswordCorrect.asStateFlow()

    init {
        _isPasswordExist.value = getString() != null
    }

    fun saveString(value1: String, value2: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (value1 == value2) {
                sharedServices.saveString(value1)
                _isPasswordExist.emit(true)
            } else {
                _isPasswordCorrect.emit(false)
            }
        }
    }

    fun getString(): String? {
        return sharedServices.getString()
    }

}