package com.bobmitchigan.com.dataaccess

import android.content.Context
import com.bobmitchigan.EventDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(EventDatabase.Schema, context, "test.db")
    }
}
