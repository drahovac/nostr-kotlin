package com.bobmitchigan.com.userProfile.dataaccess

import com.bobmitchigan.EventDatabase
import com.bobmitchigan.UserProfileEntity
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList

class UserProfileDao(database: EventDatabase) {

    private val db = database.userProfileQueries

    fun selectAll() = db.selectAll().asFlow().mapToList()

    fun createUserProfile(entity: UserProfileEntity) = db.updateOrInsert(entity)
}
