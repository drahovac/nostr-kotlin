package com.bobmitchigan.com.android.view

import org.junit.Assert.assertEquals
import org.junit.Test

internal class DestinationsTest {

    @Test
    fun `create unique route`() {
        assertEquals(
            "com.bobmitchigan.com.android.view.Destinations/MESSAGES",
            Destinations.MESSAGES.route
        )
    }
}
