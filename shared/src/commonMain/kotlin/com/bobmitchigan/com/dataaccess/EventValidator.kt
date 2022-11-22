package com.bobmitchigan.com.dataaccess

import co.touchlab.kermit.Logger
import com.soywiz.krypto.encoding.Hex

object EventValidator {

    fun hasValidId(dto: EventArrayMember.EventDto): Boolean {
        return EventSign.createEventId(dto) == dto.id
    }

    fun hasValidSignature(dto: EventArrayMember.EventDto): Boolean {
        val message = Hex.decode(dto.id.lowercase())
        val sig = Hex.decode(dto.sig.lowercase())
        val pub = Hex.decode(dto.pubkey.lowercase())

        runCatching {
            return Secp256k1Provider.verifySchnorr(signature = sig, data = message, pub = pub)
        }.getOrElse {
            Logger.e("Verify signature failed", it)
            return false
        }
    }
}
