package com.andremw96.notesnotes_kmm.android.ui.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.andremw96.notesnotes_kmm.android.theme.NotesNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NotesNotesTheme {
                NotesNotesNavigation(
                    navController = navController,
                )
            }
        }
    }
}
