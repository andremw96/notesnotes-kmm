package com.andremw96.notesnotes_kmm.android.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.andremw96.notesnotes_kmm.network.utils.Resource
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigateToNoteList: () -> Unit,
) {
    val state = viewModel.loginFormState.observeAsState(
        initial = Resource.Idle()
    ).value

    val scope = rememberCoroutineScope()

    val openDialog = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            is Resource.Loading -> {
                openDialog.value = false
                CircularProgressIndicator()
            }
            is Resource.Success -> {
                openDialog.value = false
                onNavigateToNoteList()
            }
            is Resource.Error -> {
                openDialog.value = true
                viewModel.resetState()
            }
            else -> {
                // do nothing
            }
        }

        if (openDialog.value) {
            DismissDialog(
                onDismissClicked = {
                    openDialog.value = false
                },
                title = "Something went wrong",
                bodyMessage = state.message ?: "Something went wrong"
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
            value = state.data?.username ?: "",
            onValueChange = {
                viewModel.loginDataChanged(
                    it,
                    state.data?.password ?: ""
                )
            },
            error = state.data?.usernameError ?: "",
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
            value = state.data?.password ?: "",
            onValueChange = {
                viewModel.loginDataChanged(
                    state.data?.username ?: "",
                    it
                )
            },
            error = state.data?.passwordError ?: "",
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
                scope.launch {
                    try {
                        viewModel.login(state.data?.username ?: "", state.data?.password ?: "")
                    } catch (e: Exception) {
                        e.localizedMessage ?: "error"
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, top = 12.dp),
            enabled = state.data?.isDataValid ?: false
        ) {
            Text(text = "Login", textAlign = TextAlign.Center)
        }
    }
}
