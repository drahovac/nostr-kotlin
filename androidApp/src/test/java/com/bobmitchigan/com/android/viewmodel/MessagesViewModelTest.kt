package com.bobmitchigan.com.android.viewmodel

import co.touchlab.kermit.CommonWriter
import co.touchlab.kermit.Logger
import com.bobmitchigan.com.dataaccess.SocketRepository
import com.bobmitchigan.com.domain.Event
import com.soywiz.klock.DateTime
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
    private val messagesFlow = MutableStateFlow(listOf(EVENT_1))
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
        assertEquals(listOf(EVENT_1), messagesViewModel.messages.value)
    }

    @Test
    fun `return messages from repo`() {
        messagesFlow.value = listOf(EVENT_1, EVENT_2)

        assertEquals(
            listOf(EVENT_1, EVENT_2),
            messagesViewModel.messages.value
        )
    }

    private companion object {
        val EVENT_1 = Event(
            "Message 1",
            DateTime.Companion.fromUnix(1000000),
        )
        val EVENT_2 = Event(
            "Message 2",
            DateTime.Companion.fromUnix(2000000),
        )
    }
}
