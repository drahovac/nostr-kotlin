package com.bobmitchigan.com.dataaccess

import fr.acinq.secp256k1.Secp256k1

actual object Secp256k1Provider {
    actual fun pubKeyCompress(pubkey: ByteArray) = Secp256k1.pubKeyCompress(pubkey)


    actual fun pubkeyCreate(privkey: ByteArray) = Secp256k1.pubkeyCreate(privkey)

    actual fun verifySchnorr(
        signature: ByteArray,
        data: ByteArray,
        pub: ByteArray
    ): Boolean = Secp256k1.verifySchnorr(signature, data, pub)

}