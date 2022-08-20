package com.bobmitchigan.com.dataaccess

import co.touchlab.kermit.Logger
import com.bobmitchigan.com.dataaccess.EventValidator.hasValidId
import com.bobmitchigan.com.dataaccess.EventValidator.hasValidSignature
import com.bobmitchigan.com.domain.Event
import com.soywiz.klock.DateTime
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
        check(hasValidId(dto)) { "Event has invalid id." }
        check(hasValidSignature(dto)) { "Event has invalid signature." }

        return Event(
            dto.content.orEmpty(),
            DateTime.fromUnix(dto.created * 1000)
        )
    }
}
