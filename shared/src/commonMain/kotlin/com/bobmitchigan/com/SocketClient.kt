package com.bobmitchigan.com

import com.bobmitchigan.com.domain.Message
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive

class SocketClient(private val client: HttpClient) {

    fun getMessages(): Flow<Message> {
        return flow {
            runCatching {
                client.wss(
                    method = HttpMethod.Get,
                    host = "demo.piesocket.com/v3/channel_1?api_key=VCXCEuvhGcBDP7XhiJJUDvR1e1D3eiVjgZ9VRiaV&notify_self",
                    port = 8080,
                ) {
                    emitMessages(this@flow, this)
                }
            }.getOrElse {
                println("Socket error $it")
            }
        }
    }

    private suspend fun ClientWebSocketSession.emitMessages(
        flowCollector: FlowCollector<Message>,
        defaultClientWebSocketSession: DefaultClientWebSocketSession
    ) {
        runCatching {
            while (this.isActive) {
                (incoming.receive() as? Frame.Text)?.let {
                    flowCollector.emit(Message(it.readText()))
                }
                outgoing.send(Frame.Text("I am"))
            }
        }.onFailure {
            defaultClientWebSocketSession.close()
            println("Socket closed")
        }
    }
}
