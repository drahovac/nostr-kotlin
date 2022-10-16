package com.bobmitchigan.com.injection

import com.bobmitchigan.com.dataaccess.EventDao
import com.bobmitchigan.com.dataaccess.SocketClient
import com.bobmitchigan.com.dataaccess.SocketRepository
import com.bobmitchigan.com.dataaccess.createDatabase
import com.bobmitchigan.com.domain.MessagesRepository
import com.bobmitchigan.com.userProfile.injection.userProfileModule
import io.ktor.client.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import org.koin.dsl.module

val commonModule = module {

    single {
        HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(WebSockets)
        }
    }

    single { createDatabase(get()) }

    factory { SocketClient(get()) }

    factory { EventDao(get()) }

    single<MessagesRepository> { SocketRepository(get(), get()) }
}
