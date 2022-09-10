package com.bobmitchigan.com.dataaccess

import com.bobmitchigan.EventDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(EventDatabase.Schema, "test.db")
    }
}
