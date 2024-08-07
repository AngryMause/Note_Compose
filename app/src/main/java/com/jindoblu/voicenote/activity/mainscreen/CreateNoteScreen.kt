package com.jindoblu.voicenote.activity.mainscreen

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.jindoblu.voicenote.data.model.room.NoteModel

@Composable
fun CreateNoteScreen(onBackClick: () -> Unit) {
    val viewModel: CreateNoteViewModel = hiltViewModel()
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
                    // Some works that require permission
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
            Log.e("Test", "Button onClick speechText  : $speechText")
            viewModel.saveNote(NoteModel(noteName = "id=1", note = speechText))
            onBackClick()
        }) {
            Text(text = "Save Note", color = Color.Cyan)
        }


    }
    BackHandler {
        onBackClick()
    }
}
