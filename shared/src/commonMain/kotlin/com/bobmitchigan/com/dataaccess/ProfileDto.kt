package com.bobmitchigan.com.dataaccess

import com.bobmitchigan.com.domain.Profile
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    val name: String,
    val about: String,
    val picture: String
) {
    fun toDomain(key: String) = Profile(name = name, key = key, about = about, picture = picture)
}
