package com.bobmitchigan.com.dataaccess

import co.touchlab.kermit.Logger
import com.soywiz.krypto.sha256
import fr.acinq.secp256k1.Hex
import fr.acinq.secp256k1.Secp256k1
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object EventValidator {

    fun hasValidId(dto: EventArrayMember.EventDto): Boolean {
        val pubKeyString =
            "[0," +
                    "${dto.pubkey.lowercase().encode()}," +
                    "${dto.created.encode()}," +
                    "${dto.kind.encode()}," +
                    "${dto.tags.encode()}," +
                    "${dto.content.orEmpty().encode()}]"
        val hash = pubKeyString.toByteArray(Charsets.UTF_8).sha256().hex

        return hash == dto.id
    }

    fun hasValidSignature(dto: EventArrayMember.EventDto): Boolean {
        val message = Hex.decode(dto.id.lowercase())
        val sig = Hex.decode(dto.sig.lowercase())
        val pub = Hex.decode(dto.pubkey.lowercase())

        runCatching {
            return Secp256k1.verifySchnorr(signature = sig, data = message, pub = pub)
        }.getOrElse {
            Logger.e("Verify signature failed", it)
            return false
        }
    }

    private inline fun <reified T> T.encode(): String = Json.encodeToString(this)
}
