package com.bobmitchigan.com.android.injection

import com.bobmitchigan.com.android.viewmodel.AndroidUrlMatcher
import com.bobmitchigan.com.android.viewmodel.CreateUserProfileViewModel
import com.bobmitchigan.com.android.viewmodel.MainViewModel
import com.bobmitchigan.com.android.viewmodel.MessagesViewModel
import com.bobmitchigan.com.android.viewmodel.ProfileViewModel
import com.bobmitchigan.com.android.viewmodel.UrlMatcher
import com.bobmitchigan.com.dataaccess.DriverFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {

    single { DriverFactory(get()) }

    factory<UrlMatcher> { AndroidUrlMatcher() }

    viewModel { MessagesViewModel(get()) }

    viewModel { ProfileViewModel(get()) }

    viewModel { MainViewModel(get()) }

    viewModel { CreateUserProfileViewModel(get(), get()) }
}
