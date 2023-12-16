package com.example.greenscene

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greenscene.ui.components.AlertBar
import com.example.greenscene.ui.components.BottomBar
import com.example.greenscene.ui.views.MapView
import com.example.greenscene.ui.views.log_in.LogInView
import com.example.greenscene.ui.views.profile.ProfileView
import com.example.greenscene.ui.views.sign_up.SignUpView
import kotlinx.coroutines.CoroutineScope


@Composable
fun GreenSceneApp() {

    val context = LocalContext.current
    val appState = rememberAppState()

    Scaffold(
        topBar = {
            AlertBar(context)
        },
        bottomBar = { BottomBar(appState.navController) },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        NavHost(
            navController = appState.navController,
            startDestination = MAP_SCREEN,
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

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(scaffoldState, navController, resources, coroutineScope) {
    GreenSceneAppState(scaffoldState, navController, resources, coroutineScope)
}


@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.greenSceneGraph(
    appState: GreenSceneAppState,
) {
    // todo: Splash screen

    composable(route = MAP_SCREEN) {
        MapView()
    }

    composable(PROFILE_SCREEN) {
        ProfileView(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route ->
                run {
                    Log.d("Change Route", "openScreen: $route")
                    appState.navigate(route)
                }
            }
        )
    }

    composable(SIGN_UP_SCREEN) {
        SignUpView(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
        )
    }

    composable(LOG_IN_SCREEN) {
        LogInView()
    }
}
