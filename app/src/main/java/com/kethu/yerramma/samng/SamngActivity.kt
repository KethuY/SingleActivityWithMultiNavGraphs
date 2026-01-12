package com.kethu.yerramma.samng

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kethu.yerramma.samng.featureauth.AuthGraph
import com.kethu.yerramma.samng.featureauth.CreateNewAccount
import com.kethu.yerramma.samng.featureauth.SignIn
import com.kethu.yerramma.samng.featuredashboard.DashboardGraph
import com.kethu.yerramma.samng.featuretheme.AppUiTheme
import com.kethu.yerramma.samng.featuretheme.ThemeEvent
import com.kethu.yerramma.samng.featuretheme.ThemeViewModel
import com.kethu.yerramma.samng.navgraphs.authGraph
import com.kethu.yerramma.samng.navgraphs.dashboardRootGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SamngActivity : ComponentActivity() {
    val viewModel: SamngViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel(this)
            val themeUiState = themeViewModel.state.collectAsStateWithLifecycle()
            AppUiTheme(uiTheme = themeUiState.value.theme) {
                var isSignedUser by rememberSaveable { mutableStateOf(false) }
                var isSplashScreenShowing by rememberSaveable { mutableStateOf(true) }
                var isNewUser by rememberSaveable { mutableStateOf(false) }
                splashScreen.setKeepOnScreenCondition {
                    isSplashScreenShowing // Keep showing while !isReady
                }
                LaunchedEffect(Unit) {
                    themeViewModel.onAction(ThemeEvent.GetCurrentTheme)
                    viewModel.onAction(SamngEvent.GetUserStatus)
                }
                LaunchedEffect(key1 = lifecycle) {
                    lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                        viewModel.uiEffect.collectLatest { effect ->
                            when (effect) {
                                is SamngUiEffect.LaunchAuthOrDashboardScreen -> {
                                    isNewUser = effect.isNewUser
                                    isSignedUser = effect.isSignInUser
                                    isSplashScreenShowing = false
                                }
                            }
                        }
                    }
                }
                if (!isSplashScreenShowing) {
                    val startDest = if (isSignedUser) DashboardGraph else AuthGraph
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = startDest,
                        enterTransition = { EnterTransition.None },
                        exitTransition = { ExitTransition.None },
                        popEnterTransition = { EnterTransition.None },
                        popExitTransition = { ExitTransition.None }
                    ) {
                        authGraph(navController, if (isNewUser) CreateNewAccount else SignIn)
                        dashboardRootGraph(navController, themeViewModel)
                    }
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        enableEdgeToEdge()
    }
}