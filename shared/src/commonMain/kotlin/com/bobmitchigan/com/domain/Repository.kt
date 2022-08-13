package com.bobmitchigan.com.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun setMessage(message: Message)

    fun getMessages(): Flow<Message>

    fun close()
}
