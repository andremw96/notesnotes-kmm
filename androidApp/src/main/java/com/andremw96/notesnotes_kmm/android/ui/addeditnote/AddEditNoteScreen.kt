package com.andremw96.notesnotes_kmm.android.ui.addeditnote

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.andremw96.notesnotes_kmm.android.model.NoteItem
import com.andremw96.notesnotes_kmm.android.ui.widget.NotesToolbar

@Composable
fun AddEditNoteScreen(
    navController: NavHostController,
    noteItem: NoteItem?,
) {
    Scaffold(
        topBar = {
            NotesToolbar(
                navController,
                if (noteItem == null || noteItem.title == "") "Add New Note" else "Edit ${noteItem.title}"
            )
        },
    ) {

    }
}
