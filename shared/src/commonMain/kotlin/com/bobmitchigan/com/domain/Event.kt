package com.bobmitchigan.com.domain

import kotlinx.datetime.LocalDateTime

data class Event(
    val content: String,
    val created: LocalDateTime,
)
