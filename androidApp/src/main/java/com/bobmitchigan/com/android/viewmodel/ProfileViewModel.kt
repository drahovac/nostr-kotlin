package com.bobmitchigan.com.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.bobmitchigan.com.domain.Profile
import com.bobmitchigan.com.domain.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    repository: Repository,
) : ViewModel() {
    private val _profiles: MutableStateFlow<List<Profile>> = MutableStateFlow(emptyList())
    val profiles: StateFlow<List<Profile>> = _profiles

    init {
        viewModelScope.launch {
            repository.getProfiles().collect { profiles ->
                _profiles.update { profiles }
            }
        }
    }
}
