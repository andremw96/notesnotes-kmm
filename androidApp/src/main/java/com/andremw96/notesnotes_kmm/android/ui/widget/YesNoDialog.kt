package com.andremw96.notesnotes_kmm.android.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun YesNoDialog(
    onDismissClicked: () -> Unit,
    onYesClicked: () -> Unit,
    title: String,
    bodyMessage: String,
) {
    AlertDialog(
        onDismissRequest = {
            onDismissClicked()
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(bodyMessage)
        },
        buttons = {
            Row(
                modifier = Modifier.
                    fillMaxWidth().
                    padding(all = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { onYesClicked() }
                ) {
                    Text("Yes")
                }

                Button(
                    onClick = { onDismissClicked() }
                ) {
                    Text("Dismiss")
                }
            }
        }
    )
}
