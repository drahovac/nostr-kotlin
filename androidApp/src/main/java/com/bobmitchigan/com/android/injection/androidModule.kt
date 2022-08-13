package com.bobmitchigan.com.android.injection

import com.bobmitchigan.com.android.viewmodel.MessagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { MessagesViewModel(get()) }
}
