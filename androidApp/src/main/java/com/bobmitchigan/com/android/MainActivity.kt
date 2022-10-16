package com.bobmitchigan.com.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bobmitchigan.com.android.ui.theme.KtMultiNostrTheme
import com.bobmitchigan.com.android.view.Destinations
import com.bobmitchigan.com.android.view.Destinations.CREATE_PROFILE
import com.bobmitchigan.com.android.view.Destinations.PROFILES
import com.bobmitchigan.com.android.view.Destinations.PROFILE_SELECTION
import com.bobmitchigan.com.android.viewmodel.MainViewModel
import com.bobmitchigan.com.userProfile.domain.UserProfile
import com.bobmitchigan.com.userProfile.domain.UserProfileRepository
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {

    private val detailViewModel: MainViewModel by viewModel()
    private val userProfileRepository: UserProfileRepository by inject()
    private var initState: MutableState<State> = mutableStateOf(State.LOADING)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { initState.value != State.LOADING }
        }
        super.onCreate(savedInstanceState)

        detailViewModel.init()
        setContent {
            LaunchedEffect(key1 = Unit, block = {
                userProfileRepository.getUserProfiles().collectLatest {
                    initState.value = it.toInitState()
                }
            })

            KtMultiNostrTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHoset(navController)
                }
            }
        }
    }

    @Composable
    private fun NavHoset(navController: NavHostController) {
        if (initState.value != State.LOADING) {
            NavHost(navController = navController, startDestination = initialRoute()) {
                Destinations.values().forEach { dest ->
                    composable(dest.route, content = { dest.content(navController) })
                }
            }
        }
    }

    private fun initialRoute(): String {
        return when (initState.value) {
            State.LOADING -> throw IllegalStateException("Initial dest req loading")
            State.NO_PROFILE -> CREATE_PROFILE
            State.SINGLE -> PROFILES
            State.MULTIPLE -> PROFILE_SELECTION
        }.route
    }

    private fun List<UserProfile>.toInitState(): State {
        return when {
            isEmpty() -> State.NO_PROFILE
            size == 1 -> State.SINGLE
            else -> State.MULTIPLE
        }
    }

    private enum class State {
        LOADING, NO_PROFILE, SINGLE, MULTIPLE
    }
}
