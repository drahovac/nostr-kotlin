package com.bobmitchigan.com.dataaccess

actual object Secp256k1Provider {
    actual fun pubKeyCompress(pubkey: ByteArray): ByteArray {
        TODO("Not yet implemented")
    }

    actual fun pubkeyCreate(privkey: ByteArray): ByteArray {
        TODO("Not yet implemented")
    }

    actual fun verifySchnorr(
        signature: ByteArray,
        data: ByteArray,
        pub: ByteArray
    ): Boolean {
        TODO("Not yet implemented")
    }

}