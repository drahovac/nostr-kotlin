package com.bobmitchigan.com.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun setMessage(message: Message)

    suspend fun getMessages(): Flow<Message>
}
