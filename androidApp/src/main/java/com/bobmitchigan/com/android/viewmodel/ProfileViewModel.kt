package com.bobmitchigan.com.android.viewmodel

import androidx.lifecycle.ViewModel
import com.bobmitchigan.com.domain.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {
    private val _profiles: MutableStateFlow<List<Profile>> = MutableStateFlow(
        listOf(
            Profile(
                "Example name",
                "key",
                "About"
            ),
        )
    )
    val profiles: StateFlow<List<Profile>> = _profiles
}
