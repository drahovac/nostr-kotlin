package com.bobmitchigan.com.dataaccess

import com.soywiz.krypto.sha256
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object EventValidator {

    fun hasValidId(dto: EventArrayMember.EventDto): Boolean {
        val pubKeyString =
            "[0," +
                    "${dto.pubkey.lowercase().encode()}," +
                    "${dto.created_at.encode()}," +
                    "${dto.kind.encode()}," +
                    "${dto.tags.encode()}," +
                    "${dto.content.orEmpty().encode()}]"
        val hash = pubKeyString.toByteArray(Charsets.UTF_8).sha256().hex

        return hash == dto.id
    }

    private inline fun <reified T> T.encode(): String = Json.encodeToString(this)
}
