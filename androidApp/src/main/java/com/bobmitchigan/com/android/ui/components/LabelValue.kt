package com.bobmitchigan.com.android.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bobmitchigan.com.android.ui.theme.KtMultiNostrTheme

@Composable
fun LabelValue(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    Row(modifier = Modifier) {
        TextBody1(text = "$label: ", color = MaterialTheme.colors.onBackground.copy(0.5f))
        TextBody1(text = value, modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
internal fun LabelValuePreview() {
    KtMultiNostrTheme {
        LabelValue(label = "Label", value = "Value")
    }
}
