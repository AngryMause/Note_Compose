package com.jindoblu.voicenote.activity.firstscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.jindoblu.voicenote.activity.firstscreen.item.HelloScreen
import com.jindoblu.voicenote.activity.firstscreen.item.PasswordExistScreenItem

@Composable
fun FirstScreen(access: () -> Unit) {
    val viewModel: FirstScreenViewModel = hiltViewModel()
    val isPasswordExist = viewModel.isPasswordExist.collectAsState()
    var isPasswordExistState by remember {
        mutableStateOf(isPasswordExist.value)
    }
    LaunchedEffect(key1 = isPasswordExist.value) {
        isPasswordExistState = isPasswordExist.value

    }
    var text by remember {
        mutableStateOf("")
    }
    var isPasswordCorrect by remember {
        mutableStateOf("")
    }
    if (!isPasswordExistState) {
        HelloScreen(viewModel = viewModel)
    } else {
        PasswordExistScreenItem(isPasswordCorrect, onTextListener = {
            text += it
            isPasswordCorrect += "*"
        }, onDropLastLetter = {
            text = text.dropLast(1)
            isPasswordCorrect = isPasswordCorrect.dropLast(1)
        },
            onPasswordWritten = {
                if (text == viewModel.getString()) {
                    access()
                } else {
                    text = ""
                    isPasswordCorrect = ""
                }
            })
    }
}


