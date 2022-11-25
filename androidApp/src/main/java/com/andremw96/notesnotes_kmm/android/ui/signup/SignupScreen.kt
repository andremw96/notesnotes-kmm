package com.andremw96.notesnotes_kmm.android.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andremw96.notesnotes_kmm.android.composable.OutlinedTextFieldValidation
import com.andremw96.notesnotes_kmm.android.ui.widget.DismissDialog
import com.andremw96.notesnotes_kmm.android.ui.widget.NotesToolbar

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignupScreen(
    viewModel: SignupViewModel,
    navHostController: NavHostController
) {
    val state = viewModel.signupState.observeAsState(
        initial = SignupState()
    ).value

    val keyboardController = LocalSoftwareKeyboardController.current

    val dialogError = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            NotesToolbar(navController = navHostController, title = "Signup")
        }
    ) {
        LaunchedEffect(key1 = state) {
            when {
                state.isSignupSuccess -> {
                    dialogError.value = false
                    //onNavigateToNoteList()
                }
                state.signupError != null -> {
                    dialogError.value = true
                }
            }
        }

        if (dialogError.value) {
            DismissDialog(
                onDismissClicked = {
                    dialogError.value = false
                },
                title = "Something went wrong",
                bodyMessage = state.signupError ?: "something went wrong"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextFieldValidation(
                value = state.email,
                onValueChange = {
                    viewModel.signUpDataChanged(
                        email = it,
                        username = state.username,
                        password = state.password,
                        confirmationPassword = state.confirmationPassword
                    )
                },
                error = state.emailError ?: "",
                singleLine = true,
                label = {
                    Text(text = "Enter your email")
                },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "email")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, top = 12.dp)
            )

            OutlinedTextFieldValidation(
                value = state.username,
                onValueChange = {
                    viewModel.signUpDataChanged(
                        email = state.email,
                        username = it,
                        password = state.password,
                        confirmationPassword = state.confirmationPassword
                    )
                },
                error = state.usernameError ?: "",
                singleLine = true,
                label = {
                    Text(text = "Enter your username")
                },
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "username")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, top = 12.dp)
            )

            OutlinedTextFieldValidation(
                value = state.password,
                onValueChange = {
                    viewModel.signUpDataChanged(
                        email = state.email,
                        username = state.username,
                        password = it,
                        confirmationPassword = state.confirmationPassword
                    )
                },
                error = state.passwordError ?: "",
                singleLine = true,
                label = {
                    Text(text = "Enter your password")
                },
                leadingIcon = {
                    Icon(Icons.Default.Info, contentDescription = "password")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            OutlinedTextFieldValidation(
                value = state.confirmationPassword,
                onValueChange = {
                    viewModel.signUpDataChanged(
                        email = state.email,
                        username = state.username,
                        password = state.password,
                        confirmationPassword = it
                    )
                },
                error = state.confirmationPasswordError ?: "",
                singleLine = true,
                label = {
                    Text(text = "Enter your confirmation password")
                },
                leadingIcon = {
                    Icon(Icons.Default.Info, contentDescription = "password")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            OutlinedButton(
                enabled = state.isDataValid,
                onClick = {
                    viewModel.signup(
                        email = state.email,
                        username = state.username,
                        password = state.password
                    )
                    keyboardController?.hide()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, top = 12.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Signup", textAlign = TextAlign.Center)

                    if (state.isLoading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
