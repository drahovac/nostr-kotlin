package com.bobmitchigan.com.userProfile.dataaccess

import com.bobmitchigan.UserProfileEntity
import com.bobmitchigan.com.userProfile.domain.UserProfile
import com.bobmitchigan.com.userProfile.domain.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserProfileRepositoryImpl(
    private val dao: UserProfileDao
) : UserProfileRepository {
    override suspend fun createUserProfile(
        name: String,
        about: String?,
        picture: String?
    ): Result<UserProfile> {
        TODO("Not yet implemented")
    }

    override fun getUserProfiles(): Flow<List<UserProfile>> {
        return dao.selectAll().map { it.toDomain() }
    }
}

private fun List<UserProfileEntity>.toDomain(): List<UserProfile> {
    return map {
        UserProfile(
            pubKey = it.pubkey,
            privateKey = it.privkey,
            name = it.name,
            about = it.about,
            image = it.image
        )
    }
}
