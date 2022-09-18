package com.bobmitchigan.com.dataaccess

import co.touchlab.kermit.CommonWriter
import co.touchlab.kermit.Logger
import com.bobmitchigan.EventEntityTestBuilder
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
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

    @Test
    fun `set filter`() {
        val slot = slot<EventFilter>()
        every { socketClient.setFilter(any()) } returns MutableStateFlow(null)

        socketRepository.getProfiles()

        verify { socketClient.setFilter(capture(slot)) }
    }

    @Test
    fun `parse filter event`() = runTest {
        every { socketClient.setFilter(any()) } returns MutableStateFlow(PROFILE_EVENT)

        val result = socketRepository.getProfiles().first()

        result.first().run {
            assertEquals("unclebobmartin", name)
            assertEquals(
                "Uncle Bob, Software Craftsman.  http://cleancoder.com",
                about
            )
            assertEquals(
                "https://pbs.twimg.com/profile_images/1102364992/clean_code_72_color_400x400.png",
                picture
            )
            assertEquals(
                "2ef93f01cd2493e04235a6b87b10d3c4a74e2a7eb7c3caf168268f6af73314b5",
                key
            )
        }
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
        val PROFILE_EVENT = EventArrayMember.EventDto(
            id = "9562be75aaab7bb803ec48c92d1f72b74ea00cb5a43cdd423ca337bbfafb3542",
            pubkey = "2ef93f01cd2493e04235a6b87b10d3c4a74e2a7eb7c3caf168268f6af73314b5",
            created = 1663075493,
            kind = 0,
            content = "{\"name\":\"unclebobmartin\"," +
                    "\"about\":\"Uncle Bob, Software Craftsman. " +
                    " http://cleancoder.com\",\"picture\"" +
                    ":\"https://pbs.twimg.com/profile_images" +
                    "/1102364992/clean_code_72_color_400x400.png\"}",
            sig = "1f df9a9952ccf4096814014ca6c3017631dc5f95284f8967b0a852f75" +
                    "6b82295071c7e1e62c00d84b1d9f35a122c974c51b6820c2dcdec05217d68220e78179f",
            tags = emptyList()
        )
    }
}
