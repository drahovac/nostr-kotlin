@file:UseSerializers(DateTimeSerializer::class)

package com.bobmitchigan.com.dataaccess

import com.soywiz.klock.DateTime
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
val format = Json { explicitNulls = false }

@Serializable
data class EventFilter(
    val authors: List<String>,
    val kinds: List<Int>,
    val limit: Int,
    val since: DateTime?,
) {
    fun getFilterString() = format.encodeToString(value = this)
}
