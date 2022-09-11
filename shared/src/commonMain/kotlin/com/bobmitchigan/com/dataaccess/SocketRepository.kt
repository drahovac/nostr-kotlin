@file:OptIn(ExperimentalCoroutinesApi::class)

package com.bobmitchigan.com.dataaccess

import co.touchlab.kermit.Logger
import com.bobmitchigan.EventEntity
import com.bobmitchigan.com.domain.Event
import com.bobmitchigan.com.domain.Profile
import com.bobmitchigan.com.domain.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SocketRepository(
    private val socketClient: SocketClient,
    private val eventDao: EventDao,
) : Repository {

    override suspend fun setMessage(message: Event) {
        TODO("Not yet implemented")
    }

    override suspend fun getMessages(): Flow<List<Event>> {
        return eventDao.selectAll().map { eventEntities ->
            eventEntities.map { it.toDomain() }
        }.also {
            requestNewMessages(it.first())
        }
    }

    override fun getProfiles(): Flow<List<Profile>> {
        return socketClient.setFilter(POC_EVENT_FILTER.copy(kinds = listOf(0))).map {
            it?.toProfile()?.let { listOf(it) } ?: emptyList()
        }
    }

    override suspend fun openSocket() = socketClient.openSocket()

    private suspend fun requestNewMessages(events: List<Event>) {
        val messageFlow = socketClient.getMessages(
            POC_EVENT_FILTER.copy(since = events.firstOrNull()?.created)
        )
        messageFlow.collect {
            Logger.d("Inserting message: ${it.id}")
            eventDao.insert(it)
        }
    }
}

private fun EventArrayMember.EventDto.toProfile(): Profile {
    return Profile(
        "",
        "",
        ""
    )
}

val POC_EVENT_FILTER = EventFilter(
    authors = listOf("2ef93f01cd2493e04235a6b87b10d3c4a74e2a7eb7c3caf168268f6af73314b5"),
    kinds = listOf(1),
    limit = 20,
    since = null
)

private fun EventEntity.toDomain(): Event {
    return Event(
        content = content.orEmpty(),
        created = created_at.toDateTime()
    )
}
