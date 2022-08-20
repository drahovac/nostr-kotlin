package com.bobmitchigan.com.dataaccess

import kotlinx.serialization.Serializable

@Serializable
data class EventFilter(
    val authors: List<String>,
    val kinds: List<Int>,
    val limit: Int,
)
