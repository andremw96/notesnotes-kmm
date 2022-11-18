package com.andremw96.notesnotes_kmm.android.ui.addeditnote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.andremw96.notesnotes_kmm.android.composable.OutlinedTextFieldValidation
import com.andremw96.notesnotes_kmm.android.model.NoteItem
import com.andremw96.notesnotes_kmm.android.ui.listnotes.ListNoteState
import com.andremw96.notesnotes_kmm.android.ui.widget.NotesToolbar

@Composable
fun AddEditNoteScreen(
    viewModel: AddEditNoteViewModel,
    navController: NavHostController,
    noteItem: NoteItem?,
) {
    val state = viewModel.addEditState.observeAsState(initial = AddEditNoteState(
        title = noteItem?.title ?: "",
        description = noteItem?.description ?: "",
        isNewNote = noteItem == null || noteItem.title == ""
    )).value

    Scaffold(
        topBar = {
            NotesToolbar(
                navController,
                if (state.isNewNote) "Add New Note" else "Edit ${noteItem?.title}",
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(imageVector = Icons.Filled.Save, contentDescription = "")
                    }
                }
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
                    value = state.title,
                    onValueChange = {
                        viewModel.addEditDataChanged(
                            title = it,
                            description = state.description,
                        )
                    },
                    error = state.titleError ?: "",
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
                    value = state.description,
                    onValueChange = {
                        viewModel.addEditDataChanged(
                            title = state.title,
                            description = it
                        )
                    },
                    error = state.descriptionError ?: "",
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
