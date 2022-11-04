package com.andremw96.notesnotes_kmm.android.ui.listnotes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andremw96.notesnotes_kmm.android.ui.widget.DismissDialog
import com.andremw96.notesnotes_kmm.domain.model.ListNoteSchema
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

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
                } else {
                    ListNoteScreenTextMessage(textMessage = state.error)
                }
            }
            state.listData.isEmpty() -> ListNoteScreenTextMessage("Your Notes is Empty, lets add some notes")
            state.listData.isNotEmpty() -> ListNoteList(state.listData)
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
    }
}

@Composable
fun ListNoteScreenTextMessage(
    textMessage: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = textMessage,
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

@Composable
fun ListNoteList(data: List<ListNoteSchema>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(
            items = data,
            itemContent = { item ->
                ListNoteListItem(note = item)
            }
        )
    }
}

@Composable
fun ListNoteListItem(note: ListNoteSchema) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth(),
        elevation = 8.dp,
    ) {

        Column(
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = note.title,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h5
                )
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
                val date = LocalDate.parse(note.updatedAt, formatter)
                val dateFormatted = SimpleDateFormat("dd/MM/yyyy").format(
                        Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
                )

                Text(
                    text = dateFormatted,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically),
                    style = MaterialTheme.typography.overline
                )
            }

            Text(
                text = note.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start),
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
