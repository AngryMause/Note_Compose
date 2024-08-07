package com.jindoblu.voicenote.activity.firstscreen.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jindoblu.voicenote.activity.firstscreen.FirstScreenViewModel
import com.jindoblu.voicenote.ui.theme.OutFieldsColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloScreen(viewModel: FirstScreenViewModel) {
    var firstTextPassword by remember { mutableStateOf("") }
    var secondTextPassword by remember { mutableStateOf("") }
    val isPasswordCorrect = viewModel.isPasswordCorrect.collectAsState()
    var infoAlert by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(alpha = 0.5f)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = "Hello",
                color = Black,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )

            OutlinedTextField(
                value = firstTextPassword,
                onValueChange = { firstTextPassword = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = if (isPasswordCorrect.value == false) Color.Red else OutFieldsColor,
                    unfocusedBorderColor = Gray,
                    focusedTextColor = Black,
                    cursorColor = Black
                ),
                label = { Text("Password") }
            )
            OutlinedTextField(
                value = secondTextPassword,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = if (isPasswordCorrect.value == false) Color.Red else OutFieldsColor,
                    unfocusedBorderColor = Gray,
                    focusedTextColor = Black,
                    cursorColor = Black
                ),
                onValueChange = {
                    secondTextPassword = it
                },
                label = {
                    Text("Confirm Password")
                }
            )
            Button(onClick = {
                viewModel.saveString(firstTextPassword, secondTextPassword)
            }, modifier = Modifier.padding(30.dp)) {
                Text("Save")
            }
        }

        if (infoAlert) {
            InfoAlert(onHide = { infoAlert = false }, modifier = Modifier.align(Alignment.Center))
        } else {
            IconButton(
                onClick = { infoAlert = true },
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.BottomStart)
            ) {
                Icon(Icons.Filled.Info, contentDescription = "Info")
            }
        }
    }
}


