package com.jindoblu.voicenote.activity.noteScree

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jindoblu.voicenote.activity.noteScree.composeelement.NoteItem
import com.jindoblu.voicenote.data.model.room.NoteModel

@Composable
fun NoteListScreen( onCreateNote: () -> Unit) {
    val noteListViewModel = hiltViewModel<NoteListViewModel>()
    val noteListState = noteListViewModel.noteDaos.collectAsState(initial = emptyList<NoteModel>())
    var noteList by remember {
        mutableStateOf<List<NoteModel>>(emptyList())
    }
    LaunchedEffect(key1 = noteListState.value) {
        noteList = noteListState.value
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Text(
            text = "All Notes",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.TopCenter)
        )
        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(0.9f)
                .fillMaxHeight()
        ) {
            items(noteList.size) { index ->
                NoteItem(note = noteList[index], deletedId = { id ->
                    noteListViewModel.deleteNoteById(id)
                })
            }
        }
        FloatingActionButton(
            onClick = { onCreateNote() },
            modifier = Modifier
                .padding(30.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }

    BackHandler {
    }
}


