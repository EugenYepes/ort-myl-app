package com.ar.mylapp.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ar.mylapp.components.buttons.Button7
import com.ar.mylapp.components.buttons.Button8
import com.ar.mylapp.components.text.Text8
import com.ar.mylapp.components.title.Title5
import com.ar.mylapp.ui.theme.Black
import com.ar.mylapp.ui.theme.GoldDark

@Composable
fun ConfirmDeleteDialog(
    title: String,
    description: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmButtonText: String,
    cancelButtonText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    errorMessage: String? = null
) {
    Dialog(onDismissRequest = onCancel) {
        Card(
            modifier = Modifier
                .border(width = 1.dp, color = GoldDark, shape = RoundedCornerShape(20.dp))
                .shadow(16.dp, RoundedCornerShape(20.dp))
                .background(color = Black, shape = RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(20.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Title5(title = title)
                Text8(
                    text = description,
                    maxLines = Int.MAX_VALUE
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    label = { Text("Contraseña", color = GoldDark) },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = GoldDark,
                        unfocusedTextColor = GoldDark,
                        focusedBorderColor = GoldDark,
                        unfocusedBorderColor = GoldDark,
                        focusedLabelColor = GoldDark,
                        unfocusedLabelColor = GoldDark,
                        cursorColor = GoldDark
                    )
                )

                if (!errorMessage.isNullOrBlank()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button7(
                        onClick = onCancel,
                        text = cancelButtonText
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button8(
                        onClick = onConfirm,
                        text = confirmButtonText
                    )
                }
            }
        }
    }
}