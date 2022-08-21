package com.bobmitchigan.com.dataaccess

import com.bobmitchigan.EventDatabase
import com.bobmitchigan.EventEntity

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
}