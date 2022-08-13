package com.bobmitchigan.com.android.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bobmitchigan.com.android.R
import com.bobmitchigan.com.android.viewmodel.MessagesViewModel
import com.bobmitchigan.com.domain.Message
import org.koin.androidx.compose.getViewModel

@Composable
fun MessagesScreen(viewModel: MessagesViewModel = getViewModel()) {
    val messages by viewModel.messages.collectAsState()
    MessagesContent(messages)
}

@Composable
private fun MessagesContent(messages: List<Message>) {
    LazyColumn {
        item {
            Text(
                text = stringResource(id = R.string.messages_title),
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(16.dp)
            )
        }

        items(messages) { message ->
            Text(
                text = message.text,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
