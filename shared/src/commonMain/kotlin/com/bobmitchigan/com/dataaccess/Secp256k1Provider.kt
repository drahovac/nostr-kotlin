package com.bobmitchigan.com.dataaccess

expect object Secp256k1Provider {

    fun pubKeyCompress(pubkey: ByteArray): ByteArray

    fun pubkeyCreate(privkey: ByteArray): ByteArray

    fun verifySchnorr(signature: ByteArray, data: ByteArray, pub: ByteArray): Boolean
}