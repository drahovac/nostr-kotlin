package com.bobmitchigan.com.android.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OutlineInput(
    value: String?,
    onValueChanged: (String) -> Unit,
    label: String,
    placeholder: String?,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value.orEmpty(),
        onValueChange = onValueChanged,
        label = {
            TextBody2(
                text = label
            )
        },
        placeholder = placeholder?.let {
            {
                TextBody2(
                    text = it
                )
            }
        }
    )
}

@Composable
@Preview
fun OutlineInputPreview() {
    MaterialTheme {
        OutlineInput(
            "Value",
            {},
            "Label",
            null
        )
    }
}
