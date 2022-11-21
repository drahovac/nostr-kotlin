package com.bobmitchigan.com.userProfile.domain

import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {

    suspend fun createUserProfile(
        name: String,
        about: String?,
        picture: String?
    ): Result<UserProfile>

    fun getUserProfiles(): Flow<List<UserProfile>>
}
