package com.bobmitchigan.com.userProfile.dataaccess

import com.bobmitchigan.EventDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList

class UserProfileDao(database: EventDatabase) {

    private val db = database.userProfileQueries

    fun selectAll() = db.selectAll().asFlow().mapToList()
}
