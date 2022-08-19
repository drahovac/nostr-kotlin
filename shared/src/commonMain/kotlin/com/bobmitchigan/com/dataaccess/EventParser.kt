package com.bobmitchigan.com.dataaccess

import co.touchlab.kermit.Logger
import com.bobmitchigan.com.domain.Event
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString

object EventParser {

    fun parseResponse(raw: String): Event? {
        val dto = try {
            val eventList: List<EventArrayMember> = nonStrictSerializer.decodeFromString(raw)
            eventList.filterIsInstance<EventArrayMember.EventDto>().first()
        } catch (ex: SerializationException) {
            Logger.e("Event parsing error", ex)
            return null
        }

        return Event(
            dto.content.orEmpty(),
            Instant.fromEpochSeconds(dto.created_at)
                .toLocalDateTime(TimeZone.currentSystemDefault())
        )
    }
}
