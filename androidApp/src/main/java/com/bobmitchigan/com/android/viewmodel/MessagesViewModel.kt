package com.bobmitchigan.com.android.viewmodel

import androidx.lifecycle.ViewModel
import com.bobmitchigan.com.domain.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MessagesViewModel : ViewModel() {

    val messages: StateFlow<List<Message>> = MutableStateFlow(emptyList())
}
