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
import com.andremw96.notesnotes_kmm.android.ui.widget.NotesToolbar

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignupScreen(
    navHostController: NavHostController
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            NotesToolbar(navController = navHostController, title = "Signup")
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextFieldValidation(
                value = "",
                onValueChange = {

                },
                error = "",
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
                value = "",
                onValueChange = {

                },
                error = "",
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
                value = "",
                onValueChange = {

                },
                error = "",
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
                value = "",
                onValueChange = {

                },
                error = "",
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
                onClick = {
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


                }
            }
        }
    }
}
