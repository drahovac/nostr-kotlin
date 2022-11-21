package com.bobmitchigan.com.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OutlineInput(
    value: String?,
    onValueChanged: (String) -> Unit,
    label: String,
    placeholder: String?,
    error: String? = null,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value.orEmpty(),
            onValueChange = onValueChanged,
            isError = !error.isNullOrBlank(),
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
        error?.let {
            TextBody2(
                text = it,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(top = 4.dp, start = 8.dp)
            )
        }
    }
}

@Composable
@Preview
fun OutlineInputPreview() {
    MaterialTheme {
        OutlineInput(
            "Value",
            {},
            "Label",
            null,
            "Error"
        )
    }
}
