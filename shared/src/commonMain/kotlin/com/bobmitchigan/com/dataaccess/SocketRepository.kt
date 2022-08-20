package com.bobmitchigan.com.dataaccess

import com.bobmitchigan.EventDatabase
import com.bobmitchigan.com.domain.Event
import com.bobmitchigan.com.domain.Repository
import kotlinx.coroutines.flow.Flow

class SocketRepository(
    private val socketClient: SocketClient,
    private val eventDatabase: EventDatabase
) : Repository {

    override suspend fun setMessage(message: Event) {
        TODO("Not yet implemented")
    }

    override suspend fun getMessages(): Flow<Event> = socketClient.getMessages()
}
