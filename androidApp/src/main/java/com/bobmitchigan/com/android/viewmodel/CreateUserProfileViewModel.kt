package com.bobmitchigan.com.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bobmitchigan.com.android.R
import com.bobmitchigan.com.userProfile.domain.UserProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateUserProfileViewModel(
    private val urlMatcher: UrlMatcher,
    private val userProfileRepository: UserProfileRepository,
) : ViewModel(), CreateUserProfileActions {

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
        state.value.apply {
            if (isFormValid()) {
                viewModelScope.launch {
                    userProfileRepository.createUserProfile(
                        username!!,
                        about,
                        picture
                    )
                }
            }
        }
    }

    private fun CreateProfileState.isFormValid(): Boolean {
        var result = true
        if (username.isNullOrBlank()) {
            _state.update { it.copy(usernameError = R.string.mandatory_field) }
            result = false
        }
        if (!picture.orEmpty().isValidUrl()) {
            _state.update { it.copy(pictureError = R.string.invalid_url) }
            result = false
        }
        return result
    }

    private fun String.isValidUrl(): Boolean {
        return isEmpty() || urlMatcher.isValidUrl(this)
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
    val usernameError: Int? = null,
    val about: String? = null,
    val picture: String? = null,
    val pictureError: Int? = null,
)
