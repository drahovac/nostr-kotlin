package com.bobmitchigan.com.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bobmitchigan.com.domain.Event
import com.bobmitchigan.com.domain.MessagesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MessagesViewModel(repository: MessagesRepository) : ViewModel() {

    private val _messages: MutableStateFlow<List<Event>> = MutableStateFlow(emptyList())
    val messages: StateFlow<List<Event>> = _messages

    init {
        viewModelScope.launch {
            repository.getMessages().collectLatest { messages ->
                _messages.value = messages
            }
        }
    }
}
