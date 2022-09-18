package com.bobmitchigan.com.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun setMessage(message: Event)

    suspend fun getMessages(): Flow<List<Event>>

    fun getProfiles(): Flow<List<Profile>>

    suspend fun openSocket()
}
