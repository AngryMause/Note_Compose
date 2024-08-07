package com.jindoblu.voicenote.activity.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jindoblu.voicenote.activity.descreptionScreen.DescriptionScreen
import com.jindoblu.voicenote.activity.firstscreen.FirstScreen
import com.jindoblu.voicenote.activity.mainscreen.CreateNoteScreen
import com.jindoblu.voicenote.activity.noteScree.NoteListScreen


@Composable
fun MainActivityNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationState.FIRST_SCREEN.name,
        modifier = modifier
    ) {
        composable(NavigationState.FIRST_SCREEN.name) {
            FirstScreen(access = {
                navController.navigate(NavigationState.NOTE_LIST.name)
            })
        }
        composable(NavigationState.DESCRIPTION_SCREEN.name) {
            DescriptionScreen {
                navController.popBackStack()
            }
        }
        composable(NavigationState.NOTE_LIST.name) {
            NoteListScreen(
                onCreateNote = {
                    navController.navigate(NavigationState.CREATE_NOTE.name)
                })
        }
        composable(NavigationState.CREATE_NOTE.name) {
            CreateNoteScreen(onBackClick = {
                navController.popBackStack()
            }
            )
        }
    }
}



