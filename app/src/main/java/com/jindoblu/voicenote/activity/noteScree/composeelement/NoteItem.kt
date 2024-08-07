package com.jindoblu.voicenote.activity.noteScree.composeelement

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jindoblu.voicenote.data.model.room.NoteModel

@Composable
fun NoteItem(note: NoteModel, deletedId: (Long) -> Unit, modifier: Modifier) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color.White, RoundedCornerShape(20.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
    ) {
        if (isExpanded) {
            NoteItemDescription(note = note, noteId = { id ->
                deletedId(id)
            })
        } else {
            Text(text = note.noteName, fontSize = 40.sp, color = Color.Black)
        }
        Spacer(Modifier.weight(1f))
        Icon(
            if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.Top)
                .clickable {
                    isExpanded = !isExpanded
                })
    }
}

@Composable
fun NoteItemDescription(note: NoteModel, noteId: (Long) -> Unit) {
    Column() {
        Text(
            text = note.note,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)

        )
        Icon(imageVector = Icons.Filled.Delete,
            contentDescription = "Delete Note",
            tint = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    noteId(note.id)
                })
    }
}