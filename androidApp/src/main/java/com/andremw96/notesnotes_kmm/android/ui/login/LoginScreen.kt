package com.andremw96.notesnotes_kmm.android.ui.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import com.andremw96.notesnotes_kmm.android.BuildConfig
import com.andremw96.notesnotes_kmm.android.composable.OutlinedTextFieldValidation
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigateToNoteList: () -> Unit,
) {
    val state = viewModel.loginFormState.observeAsState(
        initial = LoginFormState(
            "",
            "",
            null,
            null,
        )
    ).value

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
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
            value = state.email,
            onValueChange = {
                viewModel.loginDataChanged(
                    it,
                    state.password
                )
            },
            error = state.emailError ?: "",
            singleLine = true,
            label = {
                Text(text = "Enter your email")
            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "email")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, top = 12.dp)
        )

        OutlinedTextFieldValidation(
            value = state.password,
            onValueChange = {
                viewModel.loginDataChanged(
                    state.email,
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
                scope.launch {
                    try {
                        val text = viewModel.login(state.email, state.password)
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                        onNavigateToNoteList()
                    } catch (e: Exception) {
                        e.localizedMessage ?: "error"
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, top = 12.dp)
        ) {
            Text(text = "Login", textAlign = TextAlign.Center)
        }
    }
}
