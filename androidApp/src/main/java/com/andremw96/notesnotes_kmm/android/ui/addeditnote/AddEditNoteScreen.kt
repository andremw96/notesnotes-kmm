package com.andremw96.notesnotes_kmm.android.ui.addeditnote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.andremw96.notesnotes_kmm.android.composable.OutlinedTextFieldValidation
import com.andremw96.notesnotes_kmm.android.model.NoteItem
import com.andremw96.notesnotes_kmm.android.ui.widget.NotesToolbar

@Composable
fun AddEditNoteScreen(
    navController: NavHostController,
    noteItem: NoteItem?,
) {
    val isNewNote = remember {
        mutableStateOf(noteItem == null || noteItem.title == "")
    }

    Scaffold(
        topBar = {
            NotesToolbar(
                navController,
                if (isNewNote.value) "Add New Note" else "Edit ${noteItem?.title}"
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(16.dp),
        ) {
            ConstraintLayout {
                val (textField1, textField2) = createRefs()

                OutlinedTextFieldValidation(
                    value = noteItem?.title ?: "",
                    onValueChange = {
                    },
                    error = "",
                    singleLine = true,
                    label = {
                        Text(text = "Enter note title")
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier
                        .constrainAs(textField1) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                OutlinedTextFieldValidation(
                    value = noteItem?.description ?: "",
                    onValueChange = {

                    },
                    error = "",
                    singleLine = false,
                    label = {
                        Text(text = "Enter note description")
                    },
                    modifier = Modifier
                        .defaultMinSize(minHeight = 100.dp)
                        .constrainAs(textField2) {
                            top.linkTo(textField1.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                )
            }
        }
    }
}
