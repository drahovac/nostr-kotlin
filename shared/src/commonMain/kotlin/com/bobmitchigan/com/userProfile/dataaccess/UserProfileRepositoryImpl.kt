package com.bobmitchigan.com.userProfile.dataaccess

import com.bobmitchigan.UserProfileEntity
import com.bobmitchigan.com.dataaccess.Secp256k1Provider
import com.bobmitchigan.com.userProfile.domain.UserProfile
import com.bobmitchigan.com.userProfile.domain.UserProfileRepository
import com.soywiz.krypto.SecureRandom
import com.soywiz.krypto.encoding.Hex
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
        return runCatching {
            val private = createPrivateKey()
            val public = createPublicKey(private)
            val entity = UserProfileEntity(
                pubkey = Hex.encode(public),
                privkey = Hex.encode(private),
                name = name,
                about = about,
                image = picture
            )

            dao.createUserProfile(entity)
            entity.toDomain()
        }
    }

    private fun createPrivateKey(): ByteArray {
        val bytes = ByteArray(PRIVATE_KEY_SIZE)
        SecureRandom.nextBytes(bytes)
        return bytes
    }

    private fun createPublicKey(privateKey: ByteArray) =
        Secp256k1Provider.pubKeyCompress(Secp256k1Provider.pubkeyCreate(privateKey))
            .copyOfRange(1, PRIVATE_KEY_SIZE + 1)

    override fun getUserProfiles(): Flow<List<UserProfile>> {
        return dao.selectAll().map { it.toDomain() }
    }
}

private fun UserProfileEntity.toDomain() = UserProfile(
    pubKey = pubkey,
    privateKey = privkey,
    name = name,
    about = about,
    image = image
)

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

private const val PRIVATE_KEY_SIZE = 32
