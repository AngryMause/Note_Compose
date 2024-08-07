package com.jindoblu.voicenote.activity.noteScree.composeelement

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jindoblu.voicenote.data.model.room.NoteModel

@Composable
fun NoteItem(note: NoteModel) {
    Row(Modifier.padding(16.dp)) {
        Text(text = note.noteName, fontSize = 20.sp, color = Color.Black)
        Icon(
            Icons.Filled.ArrowDropDown,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable {

                })
    }
}