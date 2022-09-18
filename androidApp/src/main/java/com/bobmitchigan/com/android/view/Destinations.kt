package com.bobmitchigan.com.android.view

enum class Destinations {
    PROFILES,
    MESSAGES;

    val route: String
        get() = "${this::class.qualifiedName.orEmpty()}/$name"

    companion object {
        fun initialRoute() = PROFILES.route
    }
}
