package com.bobmitchigan.com.dataaccess

import co.touchlab.kermit.Logger
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive

class SocketClient(private val client: HttpClient) {

    private val filter = MutableStateFlow<EventFilter?>(null)
    private var event = MutableStateFlow<EventArrayMember.EventDto?>(null)

    fun setFilter(filter: EventFilter): Flow<EventArrayMember.EventDto?> {
        event.value = null
        this.filter.update { filter }
        return event
    }

    suspend fun openSocket() {
        Logger.d("Opening socket")
        runCatching {
            client.wss(
                method = HttpMethod.Get,
                host = Hosts.DAMUS,
            ) {
                filter.collectLatest {
                    it?.let {
                        emitMessages(it, event, this)
                    }
                }
            }
        }
    }

    fun getMessages(eventFilter: EventFilter): Flow<EventArrayMember.EventDto> {
        Logger.d("Requesting messages.")
        return flow {
            runCatching {
                client.wss(
                    method = HttpMethod.Get,
                    host = Hosts.DAMUS,
                ) {
                    emitMessages(eventFilter, this@flow, this)
                }
            }.getOrElse {
                println("Socket error $it")
            }
        }
    }

    private suspend fun ClientWebSocketSession.emitMessages(
        filter: EventFilter,
        flowCollector: FlowCollector<EventArrayMember.EventDto>,
        defaultClientWebSocketSession: DefaultClientWebSocketSession
    ) {
        runCatching {
            val filterText = filter.getFilterString()
            outgoing.send(Frame.Text("[\"REQ\", \"kotlin-multiplatform\", $filterText]"))
            while (this.isActive) {
                (incoming.receive() as? Frame.Text)?.let { dto ->
                    EventParser.parseResponse(dto.readText())?.let {
                        Logger.d("Emmiting $it")
                        flowCollector.emit(it)
                    }
                }
            }
        }.onFailure {
            defaultClientWebSocketSession.close()
            Logger.d("Socket closed, reason: ${it}")
        }
    }
}
