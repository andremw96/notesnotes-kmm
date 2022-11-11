package com.andremw96.notesnotes_kmm.android.ui.listnotes

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andremw96.notesnotes_kmm.android.ui.widget.DismissDialog
import com.andremw96.notesnotes_kmm.domain.model.ListNoteSchema
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun ListNoteScreen(
    viewModel: ListNoteViewModel,
    onNavigateLogin: () -> Unit,
    onNavigateToAddEditScreen: () -> Unit,
) {
    val state = viewModel.listNoteState.observeAsState(initial = ListNoteState()).value

    val logoutDialog = remember {
        mutableStateOf(false)
    }

    var showMenu = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Notes List")
                },
                actions = {
                    IconButton(onClick = { showMenu.value = !showMenu.value }) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "")
                    }
                    DropdownMenu(
                        expanded = showMenu.value,
                        onDismissRequest = { showMenu.value = false }
                    ) {
                        DropdownMenuItem(onClick = {
                            viewModel.logoutFromApps()
                            onNavigateLogin()
                        }) {
                            Text(text = "Logout")
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onNavigateToAddEditScreen()
            }) {
                Icon(Icons.Filled.Add, "")
            }
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

        SwipeRefresh(state = rememberSwipeRefreshState(state.isLoading), onRefresh = {
            viewModel.fetchData()
        }) {
            ListNoteList(
                data = state.listData,
                deleteItemAction = { noteItem ->
                    viewModel.deleteNoteData(noteItem)
                }
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListNoteList(
    data: List<ListNoteSchema>,
    deleteItemAction: (noteItem: ListNoteSchema) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(data, { notesList: ListNoteSchema -> notesList.id }) { item ->
            val dismissState = rememberDismissState()
            val coroutineScope = rememberCoroutineScope()

            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                coroutineScope.launch {
                    deleteItemAction(item)
                }
            }
            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier
                    .padding(vertical = Dp(1f)),
                directions = setOf(
                    DismissDirection.EndToStart
                ),
                dismissThresholds = { direction ->
                    FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                },
                background = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> Color.White
                            else -> Color.Red
                        }
                    )
                    val alignment = Alignment.CenterEnd
                    val icon = Icons.Default.Delete

                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                    )

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(horizontal = Dp(20f)),
                        contentAlignment = alignment
                    ) {
                        Icon(
                            icon,
                            contentDescription = "Delete Icon",
                            modifier = Modifier.scale(scale)
                        )
                    }
                },
            ) {
                ListNoteListItem(note = item)
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
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
        onClick = {

        }
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
