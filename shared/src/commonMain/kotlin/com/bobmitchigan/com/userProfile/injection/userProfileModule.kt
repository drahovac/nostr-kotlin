package com.bobmitchigan.com.userProfile.injection

import com.bobmitchigan.com.userProfile.dataaccess.UserProfileDao
import com.bobmitchigan.com.userProfile.dataaccess.UserProfileRepositoryImpl
import com.bobmitchigan.com.userProfile.domain.UserProfileRepository
import org.koin.dsl.module

val userProfileModule = module {

    single { UserProfileDao(get()) }

    single<UserProfileRepository> { UserProfileRepositoryImpl(get()) }
}
