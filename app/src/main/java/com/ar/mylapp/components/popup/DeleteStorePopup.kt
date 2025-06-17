package com.ar.mylapp.components.popup

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ar.mylapp.R
import com.ar.mylapp.components.dialog.DialogWithText

@Composable
fun DeleteStorePopup(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(onDismiss) {
        DialogWithText(
            title = stringResource(R.string.unvalidate_store_title),
            text = stringResource(R.string.unvalidate_store_subtitle),
            button7Text = stringResource(R.string.cancel),
            button8Text = stringResource(R.string.unvalidate_store_button_text),
            onClick = { onDismiss() },
            onConfirm = { onConfirm() },
            buttonTextSize = 11.sp
        )
    }
}