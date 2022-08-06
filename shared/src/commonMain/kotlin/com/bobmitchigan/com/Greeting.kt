package com.bobmitchigan.com

import io.ktor.client.*
import io.ktor.client.plugins.websocket.*

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }

    val client = HttpClient {
        install(WebSockets)
    }
}