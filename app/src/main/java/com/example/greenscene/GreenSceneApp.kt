package com.example.greenscene

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.greenscene.common.snackbar.SnackbarManager
import com.example.greenscene.ui.components.AlertBar
import com.example.greenscene.ui.components.BottomBar
import com.example.greenscene.ui.screens.map.MapScreen
import com.example.greenscene.ui.screens.log_in.LogInScreen
import com.example.greenscene.ui.screens.profile.ProfileScreen
import com.example.greenscene.ui.screens.sign_up.SignUpScreen
import com.example.greenscene.ui.screens.splash_screen.SplashScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun GreenSceneApp() {

    val context = LocalContext.current
    val appState = rememberAppState()

    Scaffold(
        topBar = {
            AlertBar(context)
        },
        bottomBar = { if (appState.bottomBarState.value) BottomBar(appState.navController) },
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = appState.scaffoldState.snackbarHostState,
                modifier = Modifier.padding(8.dp),
                snackbar = { snackbarData ->
                    Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                })
        },
    ) { innerPadding ->
        NavHost(
            navController = appState.navController,
            startDestination = SPLASH_SCREEN,
            modifier = Modifier.padding(innerPadding)

        ) {
            greenSceneGraph(appState)
        }
    }
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Immutable
data class TopBarState(
    val showTopBar: Boolean = false,
    @StringRes val topAppBarTitle: Int? = null,
    val onBackIconClicked: () -> Unit = {}
)


@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    resources: Resources = resources(),
    snackbarManager: SnackbarManager = SnackbarManager,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): GreenSceneAppState {
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        SPLASH_SCREEN, LOG_IN_SCREEN, SIGN_UP_SCREEN -> {
            bottomBarState.value = false
        }

        else -> bottomBarState.value = true
    }

    val appState = remember(
        scaffoldState, bottomBarState, navController, resources, snackbarManager, coroutineScope
    ) {
        GreenSceneAppState(
            scaffoldState = scaffoldState,
            navController = navController,
            bottomBarState = bottomBarState,
            snackbarManager = snackbarManager,
            resources = resources,
            coroutineScope = coroutineScope
        )
    }

    return appState
}


@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.greenSceneGraph(
    appState: GreenSceneAppState,
) {

    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(route = MAP_SCREEN) {
        MapScreen()
    }

    composable(PROFILE_SCREEN) {
        ProfileScreen(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route ->
                run {
                    appState.navigate(route)
                }
            }
        )
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) },
            popUp = appState::popUp
        )
    }

    composable(LOG_IN_SCREEN) {
        LogInScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) },
            popUp = appState::popUp
        )
    }
}
