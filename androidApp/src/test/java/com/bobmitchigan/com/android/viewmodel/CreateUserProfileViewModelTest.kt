@file:OptIn(ExperimentalCoroutinesApi::class)

package com.bobmitchigan.com.android.viewmodel

import com.bobmitchigan.com.userProfile.domain.UserProfile
import com.bobmitchigan.com.userProfile.domain.UserProfileRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

internal class CreateUserProfileViewModelTest {

    private val urlMatcher: UrlMatcher = object : UrlMatcher {
        override fun isValidUrl(value: String): Boolean {
            return value == VALID_LINK
        }
    }
    private val userProfileRepository: UserProfileRepository = mockk(relaxUnitFun = true)
    private lateinit var viewModel: CreateUserProfileViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = CreateUserProfileViewModel(urlMatcher, userProfileRepository)
    }

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
        viewModel.updatePictureLink(VALID_LINK)

        assertEquals(VALID_LINK, viewModel.state.value.picture)
    }

    @Test
    fun `set username error if not filled on submit`() {
        viewModel.updateUsername("")

        viewModel.submit()

        assertNotNull(viewModel.state.value.usernameError)
        assertNull(viewModel.state.value.pictureError)
    }

    @Test
    fun `set invalid link error if filled on submit`() {
        viewModel.updatePictureLink("invalid")
        viewModel.updateUsername(NEW_USERNAME)

        viewModel.submit()

        assertNull(viewModel.state.value.usernameError)
        assertNotNull(viewModel.state.value.pictureError)
    }

    @Test
    fun `create user profile on submit`() {
        coEvery {
            userProfileRepository.createUserProfile(
                any(),
                any(),
                any()
            )
        } returns Result.success(USER_PROFILE)
        viewModel.updatePictureLink(VALID_LINK)
        viewModel.updateUsername(NEW_USERNAME)
        viewModel.updateAbout(ABOUT)

        viewModel.submit()

        assertNull(viewModel.state.value.usernameError)
        assertNull(viewModel.state.value.pictureError)
        coVerify {
            userProfileRepository.createUserProfile(
                NEW_USERNAME, ABOUT, VALID_LINK
            )
        }
    }
}

private const val NEW_USERNAME = "NEW_USERNAME"
private const val ABOUT = "ABOUT"
private const val VALID_LINK = "www.link.cz"
private val USER_PROFILE = UserProfile(
    "",
    "",
    "",
    "",
    ""
)
