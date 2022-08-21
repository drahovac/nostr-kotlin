package com.bobmitchigan.com.dataaccess

import com.bobmitchigan.EventDatabase
import com.bobmitchigan.EventEntity
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import com.squareup.sqldelight.runtime.coroutines.mapToOneNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest

class EventDao(database: EventDatabase) {

    private val db = database.eventEntityQueries

    fun insert(event: EventArrayMember.EventDto) {
        db.insertEvent(
            EventEntity(
                id = event.id,
                pubkey = event.pubkey,
                created_at = event.created,
                kind = event.kind,
                content = event.content,
                sig = event.sig,
                tags = event.tags
            )
        )
    }

    fun selectAll() = db.selectAll().asFlow().mapToList()
}

