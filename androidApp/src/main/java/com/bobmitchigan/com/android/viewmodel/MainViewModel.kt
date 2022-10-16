package com.bobmitchigan.com.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bobmitchigan.com.domain.MessagesRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MessagesRepository) : ViewModel() {

    private var socketOpened: Boolean = false

    fun init() {
        if (!socketOpened) {
            socketOpened = true
            viewModelScope.launch {
                repository.openSocket()
            }
        }
    }
}
