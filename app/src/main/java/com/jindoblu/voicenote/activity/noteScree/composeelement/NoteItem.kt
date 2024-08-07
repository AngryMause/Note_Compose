package com.jindoblu.voicenote.activity.noteScree.composeelement

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jindoblu.voicenote.data.model.room.NoteModel

@Composable
fun NoteItem(note: NoteModel,deletedId:(Long)->Unit) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (isExpanded) {
            NoteItemDescription(note = note, noteId = {id->
                deletedId(id)
            })
        } else {
            Text(text = note.noteName, fontSize = 20.sp, color = Color.Black)
        }
        Icon(
            if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable {
                    isExpanded = !isExpanded
                })
    }
}

@Composable
fun NoteItemDescription(note: NoteModel, noteId: (Long) -> Unit) {
    Row {
        Text(
            text = note.note,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically)
                .verticalScroll(rememberScrollState())
        )
        Icon(imageVector = Icons.Filled.Delete,
            contentDescription = "Delete Note",
            tint = Color.Black,
            modifier = Modifier.clickable {
                noteId(note.id)
            })
    }
}