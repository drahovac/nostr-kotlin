package com.bobmitchigan.com.android.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateUserProfileViewModel : ViewModel(), CreateUserProfileActions {

    private val _state = MutableStateFlow(CreateProfileState())
    val state = _state.asStateFlow()

    override fun updateUsername(value: String) {
        if (USER_NAME_REGEX.matches(value)) {
            _state.update { it.copy(username = value) }
        }
    }

    override fun updateAbout(value: String) {
        _state.update { it.copy(about = value) }
    }

    override fun updatePictureLink(value: String) {
        _state.update { it.copy(picture = value) }
    }

    override fun submit() {

    }

    private companion object {
        val USER_NAME_REGEX = "^[\\w+\\-]*\$".toRegex()
    }
}

interface CreateUserProfileActions {
    fun updateUsername(value: String)

    fun updateAbout(value: String)

    fun updatePictureLink(value: String)

    fun submit()
}

data class CreateProfileState(
    val username: String? = null,
    val about: String? = null,
    val picture: String? = null,
)
