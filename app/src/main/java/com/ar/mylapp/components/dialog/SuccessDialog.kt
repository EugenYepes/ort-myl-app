package com.ar.mylapp.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.ar.mylapp.R
import com.ar.mylapp.utils.SuccessDialogType

@Composable
fun SuccessDialog(
    type: SuccessDialogType,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    val message = when (type) {
        SuccessDialogType.ADD -> stringResource(R.string.add_to_deck_success_msg)
        SuccessDialogType.DELETE -> stringResource(R.string.delete_card_from_deck_success_msg)
    }

    Dialog(onDismissRequest = { onDismiss }) {
        DialogWithText(
            title = stringResource(R.string.success),
            text = message,
            button7Text = stringResource(R.string.back),
            button8Text = stringResource(R.string.go_to_decks),
            onClick = onDismiss,
            onConfirm = onConfirm
        )
    }
}
