package com.bobmitchigan.com.dataaccess

import com.bobmitchigan.EventDatabase
import com.bobmitchigan.com.domain.Event
import com.bobmitchigan.com.domain.Repository
import com.soywiz.klock.DateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SocketRepository(
    private val socketClient: SocketClient,
    private val eventDao: EventDao,
) : Repository {

    override suspend fun setMessage(message: Event) {
        TODO("Not yet implemented")
    }

    override suspend fun getMessages(): Flow<Event> {
        val messageFlow = socketClient.getMessages()
        messageFlow.collect {
            eventDao.insert(it)
        }
        return messageFlow.map {  Event(
            it.content.orEmpty(),
            DateTime.fromUnix(it.created * MILLIS_IN_SECONDS)
        ) }
    }
}

private const val MILLIS_IN_SECONDS = 1000L
