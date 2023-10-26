package com.marmatsan.onboarding_ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SelectableButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    if (isSelected) {
        Button(
            onClick = {
                onClick()
            }
        ) {
            Text(text = text)
        }
    } else {
        OutlinedButton(
            onClick = {
                onClick()
            }
        ) {
            Text(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectableButtonPreview() {
    SelectableButton(
        text = "Selectable button",
        isSelected = true,
        onClick = {}
    )
}