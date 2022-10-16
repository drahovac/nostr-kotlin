package com.bobmitchigan.com.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bobmitchigan.com.android.ui.theme.KtMultiNostrTheme
import com.bobmitchigan.com.android.view.Destinations
import com.bobmitchigan.com.android.view.Destinations.Companion.initialRoute
import com.bobmitchigan.com.android.view.MessagesScreen
import com.bobmitchigan.com.android.view.ProfileScreen
import com.bobmitchigan.com.android.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {

    private val detailViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        detailViewModel.init()
        setContent {

            KtMultiNostrTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = initialRoute()) {
                        composable(Destinations.PROFILES.route) { ProfileScreen(navController) }
                        composable(Destinations.MESSAGES.route) { MessagesScreen() }
                    }
                }
            }
        }
    }
}
