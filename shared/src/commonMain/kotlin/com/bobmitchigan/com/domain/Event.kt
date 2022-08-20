package com.bobmitchigan.com.domain

import com.soywiz.klock.DateTime

data class Event(
    val content: String,
    val created: DateTime,
){
    fun test() {}
}
