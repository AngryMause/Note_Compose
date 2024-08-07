package com.jindoblu.voicenote.activity.mainscreen

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.jindoblu.voicenote.data.model.room.NoteModel

@Composable
fun CreateNoteScreen(onBackClick: () -> Unit) {
    val viewModel: CreateNoteViewModel = hiltViewModel()
    CreateNotesItem(onBackClick = { noteMOdel ->
        viewModel.saveNote(
            noteMOdel
        )
        onBackClick()
    }

    )
//    SpeechItem(viewModel = viewModel, onBackClick = onBackClick)
    BackHandler {
        onBackClick()
    }
}

@Composable
fun CreateNotesItem(onBackClick: (NoteModel) -> Unit) {
    val rainbowColors = listOf(
        Color.Blue,
        Color.Magenta,
        Color.Black
    )
    var noteName by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    val brush = remember {
        Brush.linearGradient(
            colors = rainbowColors
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize().safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Create Note",
            color = Color.Black,
            fontSize = 35.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        TextField(
            value = noteName, onValueChange = { noteName = it },
            textStyle = TextStyle(brush = brush),
            label = {
                Text(text = "Enter Note")
            },
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Magenta, shape = RoundedCornerShape(20.dp))
        )
        TextField(
            value = note, onValueChange = { note = it },
            textStyle = TextStyle(brush = brush),
            label = {
                Text(text = "Note", modifier = Modifier.align(Alignment.CenterHorizontally))
            },
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Magenta, shape = RoundedCornerShape(20.dp))
        )
        Button(onClick = {
            onBackClick(NoteModel(noteName = noteName, note = note))
        }) {
            Text(text = "Save Note", color = Color.White)
        }
    }

}


@Composable
fun SpeechItem(viewModel: CreateNoteViewModel, onBackClick: () -> Unit) {
    val viewModelText = viewModel.speechText.collectAsState()
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            viewModel.startVoiceInput()
        }
    }
    val context = LocalContext.current
    var speechText by remember { mutableStateOf("") }
    LaunchedEffect(key1 = viewModelText.value) {
        speechText = viewModelText.value
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.RECORD_AUDIO
                ) -> {
                    viewModel.startVoiceInput()
                }

                else -> {
                    launcher.launch(Manifest.permission.RECORD_AUDIO)
                }
            }

        }) {
            Text("Start Speaking")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = speechText, color = Color.White, fontSize = 35.sp)
        Button(onClick = {
            viewModel.saveNote(NoteModel(noteName = "id=1", note = speechText))
            onBackClick()
        }) {
            Text(text = "Save Note", color = Color.Cyan)
        }


    }
}