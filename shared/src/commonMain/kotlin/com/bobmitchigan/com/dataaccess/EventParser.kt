package com.bobmitchigan.com.dataaccess

import co.touchlab.kermit.Logger
import com.bobmitchigan.com.dataaccess.EventValidator.hasValidId
import com.bobmitchigan.com.dataaccess.EventValidator.hasValidSignature
import kotlinx.serialization.decodeFromString

object EventParser {

    fun parseResponse(raw: String): EventArrayMember.EventDto? {
        val result = runCatching {
            val eventList: List<EventArrayMember> = nonStrictSerializer.decodeFromString(raw)
            val dto = eventList.filterIsInstance<EventArrayMember.EventDto>().first()
            check(hasValidId(dto)) { "Event has invalid id." }
            check(hasValidSignature(dto)) { "Event has invalid signature." }
            dto
        }
        result.onFailure {
            Logger.e("Parsing error $it")
        }
        result.onSuccess {
            Logger.d("Parsing event success $it")
        }
        return result.getOrNull()
    }
}
