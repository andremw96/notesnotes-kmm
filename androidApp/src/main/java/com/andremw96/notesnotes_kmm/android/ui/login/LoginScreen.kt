@file:OptIn(ExperimentalComposeUiApi::class)

package com.andremw96.notesnotes_kmm.android.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andremw96.notesnotes_kmm.android.composable.OutlinedTextFieldValidation
import com.andremw96.notesnotes_kmm.android.ui.widget.DismissDialog
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigateToNoteList: () -> Unit,
) {
    val state = viewModel.loginFormState.observeAsState(
        initial = LoginFormState()
    ).value

    val scope = rememberCoroutineScope()

    val openDialog = remember {
        mutableStateOf(false)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(key1 = state) {
            when {
                state.isLoginSuccess -> {
                    openDialog.value = false
                    onNavigateToNoteList()
                }
                state.loginError != null -> {
                    openDialog.value = true
                }
            }
        }

        if (openDialog.value) {
            DismissDialog(
                onDismissClicked = {
                    openDialog.value = false
                },
                title = "Something went wrong",
                bodyMessage = state.loginError ?: "Something went wrong"
            )
        }

        Text(
            text = "Noted",
            fontFamily = FontFamily.Monospace,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        OutlinedTextFieldValidation(
            enabled = !state.isLoading,
            value = state.username,
            onValueChange = {
                viewModel.loginDataChanged(
                    it,
                    state.password
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
            enabled = !state.isLoading,
            value = state.password,
            onValueChange = {
                viewModel.loginDataChanged(
                    state.username,
                    it
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

        OutlinedButton(
            onClick = {
                keyboardController?.hide()
                scope.launch {
                    try {
                        viewModel.login(state.username, state.password)
                    } catch (e: Exception) {
                        e.localizedMessage ?: "error"
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, top = 12.dp),
            enabled = state.isDataValid
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Login", textAlign = TextAlign.Center)

                if (state.isLoading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
