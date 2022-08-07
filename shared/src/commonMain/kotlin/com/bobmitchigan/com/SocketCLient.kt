package com.bobmitchigan.com

import io.ktor.client.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*

class SocketCLient {
    val client = HttpClient {
        install(Logging)
        install(WebSockets)
    }

    suspend fun run() {
        runCatching {
            client.wss(
                method = HttpMethod.Get,
                host = "demo.piesocket.com/v3/channel_1?api_key=VCXCEuvhGcBDP7XhiJJUDvR1e1D3eiVjgZ9VRiaV&notify_self",
                port = 8080,
            ) {
                while (true) {
                    val othersMessage = incoming.receive() as? Frame.Text
                    println("vaclav " + othersMessage?.readText())
                    outgoing.send(Frame.Text("I am"))
                }
                close()
            }
        }.getOrElse {
            println("vaclav " + it)
        }
    }
}
