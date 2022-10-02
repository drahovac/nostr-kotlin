package com.bobmitchigan.com.dataaccess

import com.bobmitchigan.EventDatabase
import com.bobmitchigan.EventEntity
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList

class EventDao(database: EventDatabase) {

    private val db = database.eventEntityQueries

    fun updateOrInsert(event: EventArrayMember.EventDto) {
        db.updateOrInsert(
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

