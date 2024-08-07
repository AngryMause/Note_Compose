package com.jindoblu.voicenote.activity.firstscreen.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PasswordExistScreenItem(
    text: String,
    onTextListener: (String) -> Unit,
    onDropLastLetter: () -> Unit,
    onPasswordWritten: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.5f))
    ) {
        Text(
            text = "Enter the password",
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 30.dp)
                .align(Alignment.TopCenter)
        )

        Text(
            text = text,
            fontSize = 30.sp,
            color = Color.Black,
            textDecoration = TextDecoration.Underline,

            modifier = Modifier
                .padding(bottom = 150.dp)
                .align(Alignment.Center)
        )
        Column(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            FirstScreenItem(name1 = 1, name2 = 2, name3 = 3, onClick = {
                onTextListener(it.toString())
            })
            FirstScreenItem(name1 = 4, name2 = 5, name3 = 6, onClick = {
                onTextListener(it.toString())
            })
            FirstScreenItem(name1 = 7, name2 = 8, name3 = 9, onClick = {
                onTextListener(it.toString())
            })
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    20.dp,
                    Alignment.CenterHorizontally
                ),
            ) {
                Button(
                    onClick = {
                        onDropLastLetter()
                    },
                    colors = ButtonDefaults.buttonColors().copy(Color.Transparent),
                    modifier = Modifier
                        .background(
                            Color.DarkGray.copy(alpha = 0.5f),
                            RoundedCornerShape(20.dp)
                        )
                ) {
                    Icon(Icons.Filled.ArrowBack, "ArrowForward")
                }
                Text(
                    text = "0",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .size(100.dp, height = 50.dp)
                        .background(
                            Color.DarkGray.copy(alpha = 0.5f),
                            RoundedCornerShape(20.dp)
                        )
                        .padding(5.dp)
                        .clickable {
                            onTextListener("0")
                        },
                    color = Color.White,
                    fontSize = 35.sp
                )
                Button(
                    onClick = {
                        onPasswordWritten()
                    },
                    colors = ButtonDefaults.buttonColors().copy(Color.Transparent),
                    modifier = Modifier
                        .background(
                            Color.DarkGray.copy(alpha = 0.5f),
                            RoundedCornerShape(20.dp)
                        )
                ) {
                    Icon(Icons.Filled.Done, "ArrowForward")
                }
            }
        }
    }
}
