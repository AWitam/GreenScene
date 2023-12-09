package com.example.greenscene

import androidx.annotation.StringRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.greenscene.ui.components.AlertBar
import com.example.greenscene.ui.components.BottomBar
import com.example.greenscene.ui.views.MapView
import com.example.greenscene.ui.views.ProfileView

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Map : Screen("map", R.string.map_route, Icons.Filled.LocationOn)
    object Profile : Screen("profile", R.string.profile_route, Icons.Filled.Person)
}

val routes = listOf(
    Screen.Map,
    Screen.Profile,
)

@Composable
fun GreenSceneApp(
    navController: NavHostController = rememberNavController(),
) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            AlertBar(context)
        },
        bottomBar = { BottomBar(navController) },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Map.route,
            modifier = Modifier.padding(innerPadding)

        ) {
            composable(
                route = Screen.Map.route,
                enterTransition = {
                    fadeIn(animationSpec = tween(300, easing = LinearEasing))
                },
            ) {
                MapView()
            }
            composable(Screen.Profile.route) { ProfileView() }
        }
    }
}




