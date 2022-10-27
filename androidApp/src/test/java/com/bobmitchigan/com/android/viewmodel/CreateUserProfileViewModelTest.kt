package com.bobmitchigan.com.android.viewmodel

import org.junit.Assert.assertEquals
import org.junit.Test

internal class CreateUserProfileViewModelTest {

    private val viewModel = CreateUserProfileViewModel()

    @Test
    fun `update username`() {
        viewModel.updateUsername(NEW_USERNAME)

        assertEquals(NEW_USERNAME, viewModel.state.value.username)
    }

    @Test
    fun `filter user name input`() {
        viewModel.updateUsername(NEW_USERNAME)
        viewModel.updateUsername(" invalid")
        viewModel.updateUsername("#")

        assertEquals(NEW_USERNAME, viewModel.state.value.username)
    }

    @Test
    fun `update about`() {
        viewModel.updateAbout(ABOUT)

        assertEquals(ABOUT, viewModel.state.value.about)
    }

    @Test
    fun `update picture link`() {
        viewModel.updatePictureLink(LINK)

        assertEquals(LINK, viewModel.state.value.picture)
    }
}

private const val NEW_USERNAME = "NEW_USERNAME"
private const val ABOUT = "ABOUT"
private const val LINK = "www.link.cz"
