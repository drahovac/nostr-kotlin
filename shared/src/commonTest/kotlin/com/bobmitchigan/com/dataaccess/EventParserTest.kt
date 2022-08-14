package com.bobmitchigan.com.dataaccess

import co.touchlab.kermit.CommonWriter
import co.touchlab.kermit.Logger
import kotlin.test.*

class EventParserTest {

    @BeforeTest
    fun setUp() {
        Logger.setLogWriters(CommonWriter())
    }

    @Test
    fun `return null for empty response`() {
        val result = EventParser.parseResponse("")

        assertNull(result)
    }

    @Test
    fun `return null for invalid response`() {
        val result = EventParser.parseResponse("invalid")

        assertNull(result)
    }

    @Test
    fun `return event for valid response`() {
        val result = EventParser.parseResponse(VALID_EVENT)

        assertNotNull(result)
        assertEquals(
            "6bb60f7dd4171d915b615030bc3cd2af785188834f38f391aa6b28e6a5359ecb,1",
            result.content
        )
    }

    private companion object {
        val VALID_EVENT = """[
           "EVENT",
           "",
           {
              "id":"4d624216563b2e96273cf39f79d89bc05694018391ffc9ae2d0c9a5be39eee6e",
              "pubkey":"1a959462676a7eb45f479d4f050d9922b0b2fc851a669f3eb96f9949b53741c0",
              "created_at":7,
              "kind":1,
              "tags":[
                 
              ],
              "content":"6bb60f7dd4171d915b615030bc3cd2af785188834f38f391aa6b28e6a5359ecb,1",
              "sig":"8dab597311989986718091a1482025d51ef6396426adc66a7d6e4c0a8a102f83b332c8d987bba0c7972c00caa91c3c1ccfab5180d839d4626f4df7334f065082"
           }
        ]""".trimIndent()
    }
}
