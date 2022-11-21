package com.bobmitchigan.com.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.bobmitchigan.com.android.ui.theme.KtMultiNostrTheme

@Composable
fun TextBody1(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.body1,
        modifier = modifier,
        textAlign = textAlign,
    )
}

@Composable
fun TextBody2(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.body2,
        modifier = modifier,
        textAlign = textAlign,
    )
}

@Composable
fun TextHeadline3(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h3,
        modifier = modifier,
        textAlign = textAlign,
        color = color
    )
}

@Preview
@Composable
internal fun TextPreview() {
    KtMultiNostrTheme {
        Column {
            TextBody1(text = "Text body 1")
            TextBody2(text = "Text body 2")
            TextHeadline3(text = "Headline 3")
        }
    }
}
