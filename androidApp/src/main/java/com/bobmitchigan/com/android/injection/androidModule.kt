package com.bobmitchigan.com.android.injection

import com.bobmitchigan.com.android.viewmodel.MessagesViewModel
import com.bobmitchigan.com.dataaccess.DriverFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {

    single { DriverFactory(get()) }

    viewModel { MessagesViewModel(get()) }
}
