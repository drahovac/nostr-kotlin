package com.bobmitchigan.com.injection

import com.bobmitchigan.com.dataaccess.SocketRepository
import com.bobmitchigan.com.domain.Repository
import io.ktor.client.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import org.koin.dsl.module

val commonModule = module {

    single {
        HttpClient {
            install(Logging)
            install(WebSockets)
        }
    }

    factory<Repository> { SocketRepository(get()) }
}
