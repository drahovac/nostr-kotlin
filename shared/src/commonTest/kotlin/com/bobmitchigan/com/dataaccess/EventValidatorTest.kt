package com.bobmitchigan.com.dataaccess

import co.touchlab.kermit.CommonWriter
import co.touchlab.kermit.Logger
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EventValidatorTest {

    @BeforeTest
    fun setUp() {
        Logger.setLogWriters(CommonWriter())
    }

    @Test
    fun `return negative for invalid event id`() {
        val result = EventValidator.hasValidId(
            EventArrayMember.EventDto(
                id = "8fd92e9525e8d8647895f5b5de0668d66bfff57eec075959f48aa2941d174040",
                pubkey = "2ef93f01cd2493e04235a6b87b10d3c4a74e2a7eb7c3caf168268f6af73314b5",
                created = 1660826825,
                kind = 1,
                tags = listOf(listOf("client", "more-speech - 202208171549")),
                content = "test",
                sig = "e4d8edef7fdc8b63f256f7fe006961768b1a23c01f34b825eea0897cc94cc204a728a" +
                        "615a880ae7f823577c6a97266f77f160f9174dc50d405d2e22c365d1700"
            )
        )

        assertFalse(result)
    }

    @Test
    fun `return positive for valid event id`() {
        val result = EventValidator.hasValidId(VALID_EVENT)

        assertTrue(result)
    }

    @Test
    fun `return negative for invalid signature`() {
        val result =
            EventValidator.hasValidSignature(
                VALID_EVENT.copy(
                    sig = "fc0a3382871b69c5ca0e5f6127c858a1f587a0e01df520ac5939ac5a934081dd292" +
                            "ca66c6b7e923b780ea997ea230a3e80af57f3bc5aceb20a69b898d6b792f1"
                )
            )

        assertFalse(result)
    }

    @Test
    fun `return positive for valid signature`() {
        val result =
            EventValidator.hasValidSignature(
                VALID_EVENT
            )

        assertTrue(result)
    }

    private companion object {
        val VALID_EVENT = EventArrayMember.EventDto(
            id = "87c5e5c54645c0aaaa381db98ad9e4769b0260ede94a1e0c9615f75637f94a3f",
            pubkey = "2ef93f01cd2493e04235a6b87b10d3c4a74e2a7eb7c3caf168268f6af73314b5",
            created = 1660826825,
            kind = 1,
            tags = listOf(listOf("client", "more-speech - 202208171549")),
            content = "test",
            sig = "e4d8edef7fdc8b63f256f7fe006961768b1a23c01f34b825eea0897cc9" +
                    "4cc204a728a615a880ae7f823577c6a97266f77f160f9174dc50d405d2e22c365d1700"
        )
    }
}
