package com.bobmitchigan.com

import co.touchlab.kermit.Logger
import com.bobmitchigan.com.dataaccess.EventFilter
import com.bobmitchigan.com.dataaccess.EventParser
import com.bobmitchigan.com.domain.Event
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SocketClient(private val client: HttpClient) {

    fun getMessages(): Flow<Event> {
        return flow {
            runCatching {
                client.wss(
                    method = HttpMethod.Get,
                    host = "nostr-pub.wellorder.net",
                ) {
                    emitMessages(this@flow, this)
                }
            }.getOrElse {
                println("Socket error $it")
            }
        }
    }

    private suspend fun ClientWebSocketSession.emitMessages(
        flowCollector: FlowCollector<Event>,
        defaultClientWebSocketSession: DefaultClientWebSocketSession
    ) {
        runCatching {
            val filterText = EventFilter(
                authors = listOf("2ef93f01cd2493e04235a6b87b10d3c4a74e2a7eb7c3caf168268f6af73314b5"),
                kinds = listOf(1),
                limit = 10,
            ).let {
                Json.encodeToString(value = it)
            }
            outgoing.send(Frame.Text("[\"REQ\", \"kotlin-multiplatform\", $filterText]"))
            while (this.isActive) {
                (incoming.receive() as? Frame.Text)?.let {
                    Logger.d(it.readText())
                    EventParser.parseResponse(it.readText())?.let {
                        flowCollector.emit(it)
                    }
                }
            }
        }.onFailure {
            defaultClientWebSocketSession.close()
            Logger.d("Socket closed")
        }
    }
}
