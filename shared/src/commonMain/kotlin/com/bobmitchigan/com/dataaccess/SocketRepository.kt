package com.bobmitchigan.com.dataaccess

import com.bobmitchigan.EventEntity
import com.bobmitchigan.com.domain.Event
import com.bobmitchigan.com.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SocketRepository(
    private val socketClient: SocketClient,
    private val eventDao: EventDao,
) : Repository {

    override suspend fun setMessage(message: Event) {
        TODO("Not yet implemented")
    }

    override suspend fun getMessages(): Flow<List<Event>> {
        val messageFlow = socketClient.getMessages()
        messageFlow.collect {
            eventDao.insert(it)
        }
        return eventDao.selectAll().map { it.map { it.toDomain() } }
    }
}

private fun EventEntity.toDomain(): Event {
    return Event(
        content = content.orEmpty(),
        created = created_at.toDateTime()
    )
}
