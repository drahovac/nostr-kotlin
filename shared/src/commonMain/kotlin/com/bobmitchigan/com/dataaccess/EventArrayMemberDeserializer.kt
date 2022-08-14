package com.bobmitchigan.com.dataaccess

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

object EventArrayMemberDeserializer :
    JsonContentPolymorphicSerializer<EventArrayMember>(EventArrayMember::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out EventArrayMember> {
        return when (element) {
            is JsonPrimitive -> EventArrayMember.StringValue.serializer()
            else -> EventArrayMember.EventDto.serializer()
        }
    }
}

object StringValueSerializer : KSerializer<EventArrayMember.StringValue> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("StringValue")

    override fun deserialize(decoder: Decoder): EventArrayMember.StringValue {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        return EventArrayMember.StringValue(element.jsonPrimitive.content)
    }

    override fun serialize(encoder: Encoder, value: EventArrayMember.StringValue) {
        encoder.encodeString(value.value)
    }
}
