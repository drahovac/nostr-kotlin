package com.bobmitchigan.com.userProfile.domain

import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    fun getUserProfiles(): Flow<List<UserProfile>>
}
