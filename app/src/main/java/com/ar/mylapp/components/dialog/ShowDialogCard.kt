package com.ar.mylapp.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.Red
import com.ar.mylapp.utils.DialogType

@Composable
fun ShowDialogCard(
    dialogType: DialogType?,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
) {
    val (title, message, borderColor) = when (dialogType) {
        DialogType.ADD_SUCCESS -> Triple(
            stringResource(R.string.success),
            stringResource(R.string.add_to_deck_success_msg),
            GoldDark
        )
        DialogType.DELETE_SUCCESS -> Triple(
            stringResource(R.string.success),
            stringResource(R.string.delete_card_from_deck_success_msg),
            GoldDark
        )
        DialogType.ADD_FAIL -> Triple(
            stringResource(R.string.fail),
            stringResource(R.string.add_to_deck_error_msg),
            Red
        )
        DialogType.DELETE_FAIL -> Triple(
            stringResource(R.string.fail),
            stringResource(R.string.delete_card_from_deck_error_msg),
            Red
        )
        null -> Triple("", "", GoldDark)
    }

    Dialog(onDismissRequest = onDismissRequest) {
        DialogWithText(
            title = title,
            text = message,
            button7Text = stringResource(R.string.back),
            button8Text = stringResource(R.string.go_to_decks),
            onClick = onDismissRequest,
            onConfirm = onConfirm,
            borderColor = borderColor
        )
    }
}