package com.bobmitchigan.com.android

import android.app.Application
import com.bobmitchigan.com.android.injection.androidModule
import com.bobmitchigan.com.injection.commonModule
import com.bobmitchigan.com.userProfile.injection.userProfileModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NostrKtApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NostrKtApp)
            androidLogger()
            modules(commonModule, userProfileModule, androidModule)
        }
    }
}
