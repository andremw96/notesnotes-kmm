package com.andremw96.notesnotes_kmm.android.ui.listnotes

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andremw96.notesnotes_kmm.android.ui.widget.DismissDialog

@Composable
fun ListNoteScreen(
    viewModel: ListNoteViewModel,
    onNavigateLogin: () -> Unit,
) {
    val state = viewModel.listNoteState.observeAsState(initial = ListNoteState()).value

    val logoutDialog = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Notes List")
                }
            )
        }
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.error != null -> {
                if (state.error == "Unauthorized") {
                    logoutDialog.value = true
                }
            }
        }

        if (logoutDialog.value) {
            DismissDialog(
                onDismissClicked = {
                    logoutDialog.value = false

                    viewModel.logoutFromApps()
                    onNavigateLogin()
                },
                title = "Something went wrong",
                bodyMessage = state.error ?: "Something went wrong"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Noted LIST SCREEN",
                fontFamily = FontFamily.Monospace,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )
        }
    }
}
