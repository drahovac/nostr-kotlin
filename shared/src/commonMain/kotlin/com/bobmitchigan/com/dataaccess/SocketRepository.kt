package com.bobmitchigan.com.dataaccess

import com.bobmitchigan.com.SocketClient
import com.bobmitchigan.com.domain.Message
import com.bobmitchigan.com.domain.Repository
import kotlinx.coroutines.flow.Flow

class SocketRepository(private val socketClient: SocketClient) : Repository {

    override suspend fun setMessage(message: Message) {
        TODO("Not yet implemented")
    }

    override suspend fun getMessages(): Flow<Message> = socketClient.getMessages()
}
