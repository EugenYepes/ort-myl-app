package com.ar.mylapp.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun DeleteDeckConfirmationDialog(
    id : Int,
    title: String,
    text: String,
    button7Text: String,
    button8Text: String,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        DialogWithText(
            title = title,
            text = text,
            button7Text = button7Text,
            button8Text = button8Text,
            onClick = onDismiss,
            onConfirm = { onConfirm(id) }
        )
    }
}