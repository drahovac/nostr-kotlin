package com.bobmitchigan.com.dataaccess

import com.bobmitchigan.com.domain.Message
import com.bobmitchigan.com.domain.Repository
import io.ktor.client.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SocketRepository(private val client: HttpClient) : Repository {

    override suspend fun setMessage(message: Message) {
        TODO("Not yet implemented")
    }

    override fun getMessages(): Flow<Message> {
        return flow { }
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}
