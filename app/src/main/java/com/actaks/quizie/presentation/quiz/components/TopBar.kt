package com.actaks.quizie.presentation.quiz.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.actaks.quizie.presentation.theme.QuizieTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onExitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(
                onClick = onExitClick
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Exit"
                )
            }
        }
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    QuizieTheme {
        TopBar(
            onExitClick = {},
            title = "Android Quiz"
        )
    }
}