package com.bobmitchigan.com.android.viewmodel

import co.touchlab.kermit.CommonWriter
import co.touchlab.kermit.Logger
import com.bobmitchigan.com.dataaccess.SocketRepository
import com.bobmitchigan.com.domain.Message
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class MessagesViewModelTest {

    private val repository: SocketRepository = mockk()
    private val messagesFlow = MutableStateFlow<Message>(MESSAGE_1)
    private lateinit var messagesViewModel: MessagesViewModel

    @Before
    fun setUp() {
        Logger.setLogWriters(CommonWriter())
        coEvery { repository.getMessages() } returns messagesFlow
        Dispatchers.setMain(Dispatchers.Unconfined)
        messagesViewModel = MessagesViewModel(repository)
    }

    @Test
    fun `return first messages on init`() {
        assertEquals(listOf(MESSAGE_1), messagesViewModel.messages.value)
    }

    @Test
    fun `return messages from repo`() {
        messagesFlow.value = MESSAGE_2

        assertEquals(
            listOf(MESSAGE_1, MESSAGE_2),
            messagesViewModel.messages.value
        )
    }

    private companion object {
        val MESSAGE_1 = Message("Message 1")
        val MESSAGE_2 = Message("Message 2")
    }
}
