package com.ar.mylapp.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun EditDeckConfirmationDialog(
    title: String,
    button8Text: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        DialogWithoutText(
            title = title,
            button8Text = button8Text,
            onClick = onDismiss
        )
    }
}

