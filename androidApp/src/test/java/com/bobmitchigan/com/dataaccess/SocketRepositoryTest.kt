package com.bobmitchigan.com.dataaccess

import co.touchlab.kermit.CommonWriter
import co.touchlab.kermit.Logger
import com.bobmitchigan.EventEntityTestBuilder
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SocketRepositoryTest {
    private val socketClient: SocketClient = mockk()
    private val eventDao: EventDao = mockk(relaxUnitFun = true)
    private val messagesFlow: Flow<EventArrayMember.EventDto> = flowOf()
    private val socketRepository = SocketRepository(socketClient, eventDao)

    @Before
    fun setUp() {
        Logger.setLogWriters(CommonWriter())
        every { socketClient.getMessages(any()) } returns messagesFlow
    }

    @Test
    fun `should request messages without start date if db empty`() = runTest {
        every { eventDao.selectAll() } returns flowOf(emptyList())

        socketRepository.getMessages()

        verify { socketClient.getMessages(POC_EVENT_FILTER) }
    }

    @Test
    fun `should request messages from newest message in db empty`() = runTest {
        every { eventDao.selectAll() } returns flowOf(ENTITIES)

        socketRepository.getMessages()

        verify { socketClient.getMessages(POC_EVENT_FILTER.copy(since = NEWEST_DATE.toDateTime())) }
    }

    @Test
    fun `insert new messages to db`() = runTest {
        every { socketClient.getMessages(any()) } returns flowOf(NEW_EVENT)
        every { eventDao.selectAll() } returns flowOf(emptyList())

        socketRepository.getMessages()

        verify { eventDao.insert(NEW_EVENT) }
    }

    @Test
    fun `open socket`() = runTest {
        coEvery { socketClient.openSocket() } returns Unit

        socketRepository.openSocket()

        coVerify { socketClient.openSocket() }
    }

    private companion object {
        const val NEWEST_DATE = 1000L
        val EVENT_ENTITY_NEWEST = EventEntityTestBuilder(
            id = "ID1",
            created_at = NEWEST_DATE
        ).build()
        val EVENT_ENTITY_MIDDLE = EventEntityTestBuilder(
            id = "ID2",
            created_at = 500
        ).build()
        val EVENT_ENTITY_LAST = EventEntityTestBuilder(
            id = "ID3",
            created_at = 0
        ).build()
        val ENTITIES = listOf(EVENT_ENTITY_NEWEST, EVENT_ENTITY_MIDDLE, EVENT_ENTITY_LAST)
        val NEW_EVENT = EventArrayMember.EventDto(
            "IDNEW",
            "pubkey",
            2000L,
            1,
            "Content",
            "Signature",
            listOf()
        )
    }
}
