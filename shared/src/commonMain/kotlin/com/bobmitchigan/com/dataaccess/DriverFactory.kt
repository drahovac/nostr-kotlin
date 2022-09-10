package com.bobmitchigan.com.dataaccess

import com.bobmitchigan.EventEntity
import com.bobmitchigan.EventDatabase
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): EventDatabase {
    val driver = driverFactory.createDriver()

    return EventDatabase(driver, EventEntity.Adapter(listOfStringsAdapter))
}

val listOfStringsAdapter = object : ColumnAdapter<List<List<String>>, String> {
    override fun decode(databaseValue: String): List<List<String>> {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: List<List<String>>): String {
        return Json.encodeToString(value)
    }
}
