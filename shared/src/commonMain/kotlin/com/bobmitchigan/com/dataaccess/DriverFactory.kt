package com.bobmitchigan.com.dataaccess

import com.bobmitchigan.EventDatabase
import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): EventDatabase {
    val driver = driverFactory.createDriver()

    return EventDatabase(driver)
}
