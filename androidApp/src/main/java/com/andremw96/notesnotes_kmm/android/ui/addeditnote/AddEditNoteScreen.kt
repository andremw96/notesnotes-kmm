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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.andremw96.notesnotes_kmm.android.composable.OutlinedTextFieldValidation
import com.andremw96.notesnotes_kmm.android.ui.widget.NotesToolbar

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddEditNoteScreen(
    viewModel: AddEditNoteViewModel,
    navController: NavHostController,
    onNavigateToNoteList: () -> Unit,
) {
    val state = viewModel.addEditState.observeAsState(
        initial = AddEditNoteState()
    ).value

    LaunchedEffect(key1 = state) {
        if (state.saveNoteSuccess) {
            onNavigateToNoteList()
        }
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            NotesToolbar(
                navController = navController,
                title = if (state.isNewNote) "Add New Note" else "Edit ${state.title}",
                actions = {
                    IconButton(
                        enabled = state.isDataValid,
                        onClick = {
                            keyboardController?.hide()

                            if (state.isNewNote) {
                                viewModel.saveNote(
                                    state.title,
                                    state.description,
                                )
                            }
                        }
                    ) {
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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            }

            ConstraintLayout {
                val (textField1, textField2) = createRefs()

                OutlinedTextFieldValidation(
                    enabled = !state.isLoading,
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
                    enabled = !state.isLoading,
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
