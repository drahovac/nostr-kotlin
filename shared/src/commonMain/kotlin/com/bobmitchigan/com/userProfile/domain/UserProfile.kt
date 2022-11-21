package com.bobmitchigan.com.userProfile.domain

data class UserProfile(
    val pubKey: String,
    val privateKey: String,
    val name: String,
    val about: String?,
    val image: String?
)
