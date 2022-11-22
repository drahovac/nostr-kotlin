package com.bobmitchigan.com.dataaccess

import com.soywiz.krypto.sha256
import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.toByteArray
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object EventSign {

    fun createEventId(dto: EventArrayMember.EventDto): String {
        val pubKeyString =
            "[0," +
                    "${dto.pubkey.lowercase().encode()}," +
                    "${dto.created.encode()}," +
                    "${dto.kind.encode()}," +
                    "${dto.tags.encode()}," +
                    "${dto.content.orEmpty().encode()}]"
        return pubKeyString.toByteArray(Charsets.UTF_8).sha256().hex
    }

    private inline fun <reified T> T.encode(): String = Json.encodeToString(this)
}