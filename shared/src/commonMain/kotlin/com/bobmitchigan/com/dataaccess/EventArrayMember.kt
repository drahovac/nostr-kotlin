package com.bobmitchigan.com.dataaccess

import kotlinx.serialization.Serializable

@Serializable(with = EventArrayMemberDeserializer::class)
sealed interface EventArrayMember {

    @Serializable(with = StringValueSerializer::class)
    data class StringValue(val value: String) : EventArrayMember

    @Serializable
    data class EventDto(
        val id: String,
        val pubkey: String,
        val created_at: Long,
        val kind: Int,
        val content: String?,
        val sig: String,
    ) : EventArrayMember
}