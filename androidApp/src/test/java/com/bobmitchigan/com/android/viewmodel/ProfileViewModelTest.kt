package com.bobmitchigan.com.android.viewmodel

import com.bobmitchigan.com.domain.Repository
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class ProfileViewModelTest {

    private val repository: Repository = mockk()
    private lateinit var profileViewModel: ProfileViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        profileViewModel = ProfileViewModel(repository)
    }

    @Test
    fun `collect profiles on init`() {
        verify { repository.getProfiles() }
    }
}
