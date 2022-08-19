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
            "test",
            result.content,
        )
        assertEquals(
            "2022-08-18T14:47:05",
            result.created.toString()
        )
    }

    private companion object {
        val VALID_EVENT = """[
             "EVENT",
             "kotlin-multiplatform",
             {
                "id":"87c5e5c54645c0aaaa381db98ad9e4769b0260ede94a1e0c9615f75637f94a3f",
                "pubkey":"2ef93f01cd2493e04235a6b87b10d3c4a74e2a7eb7c3caf168268f6af73314b5",
                "created_at":1660826825,
                "kind":1,
                "tags":[
                   [
                      "client",
                      "more-speech - 202208171549"
                   ]
                ],
                "content":"test",
                "sig":"e4d8edef7fdc8b63f256f7fe006961768b1a23c01f34b825eea0897cc94cc204a728a615a880ae7f823577c6a97266f77f160f9174dc50d405d2e22c365d1700"
             }
        ]""".trimIndent()
    }
}
