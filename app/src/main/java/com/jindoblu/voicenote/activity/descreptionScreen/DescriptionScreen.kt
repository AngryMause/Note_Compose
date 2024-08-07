package com.jindoblu.voicenote.activity.descreptionScreen

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
fun DescriptionScreen(onBack:()->Unit) {


    BackHandler {
        onBack()
    }

}