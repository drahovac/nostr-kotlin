package com.bobmitchigan.com

import io.ktor.client.*
import io.ktor.client.plugins.websocket.*

class Greeting {
    val client = HttpClient {
        install(WebSockets)
    }
}
