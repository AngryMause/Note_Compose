package com.jindoblu.voicenote.activity.firstscreen.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jindoblu.voicenote.R

@Composable
fun InfoAlert(onHide: () -> Unit, modifier: Modifier) {
    Column(
        modifier = modifier
            .border(3.dp, Color.DarkGray, shape = RoundedCornerShape(20.dp))
            .background(Color.LightGray, shape = RoundedCornerShape(20.dp))
            .fillMaxHeight(0.4f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = stringResource(id = R.string.firts_screen_info),
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp,
            fontWeight = FontWeight.W300,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "close",
            tint = Color.Black,
            modifier = Modifier
                .border(3.dp, Color.Black, shape = RoundedCornerShape(20.dp))
                .padding(5.dp)
                .size(30.dp)
                .clickable { onHide() }
                .align(Alignment.CenterHorizontally)
        )

    }
}