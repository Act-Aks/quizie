package com.actaks.quizie.presentation.dashboard.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.actaks.quizie.presentation.theme.QuizieTheme

@Composable
fun EditNameDialog(
    value: String,
    onValueChange: (String) -> Unit,
    isOpen: Boolean,
    modifier: Modifier = Modifier,
    title: String = "Edit your name",
    confirmButtonLabel: String = "Rename",
    dismissButtonLabel: String = "Cancel",
    onDismissDialog: () -> Unit,
    onConfirmDialog: () -> Unit,
    error: String? = null
) {
    if (isOpen)
        AlertDialog(
            modifier = modifier,
            title = { Text(text = title) },
            text = {
                OutlinedTextField(
                    value = value,
                    onValueChange = { onValueChange(it) },
                    singleLine = true,
                    isError = !error.isNullOrEmpty() && value.isNotEmpty(),
                    supportingText = { Text(text = error.orEmpty()) }
                )
            },
            onDismissRequest = onDismissDialog,
            confirmButton = {
                TextButton(onClick = onConfirmDialog) {
                    Text(text = confirmButtonLabel)
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissDialog) {
                    Text(text = dismissButtonLabel)
                }
            }
        )
}

@Preview
@Composable
private fun EditNameDialogPreview() {
    QuizieTheme {
        EditNameDialog(
            value = "",
            isOpen = true,
            onValueChange = {},
            onDismissDialog = {},
            onConfirmDialog = {},
        )
    }
}
