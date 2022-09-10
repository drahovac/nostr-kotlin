package com.bobmitchigan.com.dataaccess

import com.soywiz.klock.DateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DateTimeSerializer : KSerializer<DateTime> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("com.soywiz.klock.DateTime", PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): DateTime {
        return decoder.decodeLong().toDateTime()
    }

    override fun serialize(encoder: Encoder, value: DateTime) {
        encoder.encodeLong(value.unixMillisLong)
    }
}