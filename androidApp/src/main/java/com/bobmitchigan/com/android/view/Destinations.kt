package com.bobmitchigan.com.android.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

enum class Destinations(val content: @Composable (NavController) -> Unit) {
    CREATE_PROFILE({ CreateUserProfileScreen() }),
    PROFILE_SELECTION({}),
    PROFILES({ ProfileScreen(it) }),
    MESSAGES({ MessagesScreen() });

    val route: String
        get() = "${this::class.qualifiedName.orEmpty()}/$name"
}
