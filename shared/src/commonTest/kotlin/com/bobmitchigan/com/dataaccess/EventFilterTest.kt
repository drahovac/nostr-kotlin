package com.bobmitchigan.com.dataaccess

import com.soywiz.klock.DateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class EventFilterTest {

    @Test
    fun `create filter text no date`() {
        val filterText = EventFilter(
            listOf("author1", "author2"),
            listOf(0, 2),
            20,
            null
        ).getFilterString()

        assertEquals(
            "{\"authors\":[\"author1\",\"author2\"],\"kinds\":[0,2],\"limit\":20}",
            filterText
        )
    }

    @Test
    fun `create filter text`() {
        val filterText = EventFilter(
            listOf("author1", "author2"),
            listOf(0, 2),
            20,
            DateTime.fromUnix(5000),
        ).getFilterString()

        assertEquals(
            "{\"authors\":[\"author1\",\"author2\"],\"kinds\":[0,2],\"limit\":20,\"since\":5000}",
            filterText
        )
    }
}
