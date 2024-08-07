package com.jindoblu.voicenote.activity.firstscreen.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FirstScreenItem(name1: Int, name2: Int, name3: Int, onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
    ) {
        Text(
            text = "$name1",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.DarkGray.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                .padding(20.dp)
                .clickable {
                    onClick(name1)
                },
            color = Color.White,
            fontSize = 35.sp
        )
        Text(
            text = "$name2",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.DarkGray.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                .padding(20.dp)
                .clickable {
                    onClick(name2)
                },
            color = Color.White,
            fontSize = 35.sp
        )
        Text(
            text = "$name3",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.DarkGray.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                .padding(20.dp)
                .clickable {
                    onClick(name3)
                },
            color = Color.White,
            fontSize = 35.sp
        )
    }
}

