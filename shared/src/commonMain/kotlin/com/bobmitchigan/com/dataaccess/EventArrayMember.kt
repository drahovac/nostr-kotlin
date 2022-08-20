package com.bobmitchigan.com.dataaccess

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = EventArrayMemberDeserializer::class)
sealed interface EventArrayMember {

    @Serializable(with = StringValueSerializer::class)
    data class StringValue(val value: String) : EventArrayMember

    @Serializable
    data class EventDto(
        val id: String,
        val pubkey: String,
        @SerialName("created_at")
        val created: Long,
        val kind: Int,
        val content: String?,
        val sig: String,
        val tags: List<List<String>>,
    ) : EventArrayMember
}
