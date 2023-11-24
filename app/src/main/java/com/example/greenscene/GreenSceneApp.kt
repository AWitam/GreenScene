package com.example.greenscene

import androidx.annotation.StringRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.greenscene.views.MapView
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.greenscene.ui.theme.NavigationBarHeight
import com.example.greenscene.views.ProfileView


@Composable
fun GreenSceneApp(navController: NavHostController = rememberNavController()) {
    Scaffold(
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
            ) { MapView(navController) }
            composable(Screen.Profile.route) { ProfileView() }
        }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Map : Screen("map", R.string.map_route, Icons.Filled.LocationOn)
    object Profile : Screen("profile", R.string.profile_route, Icons.Filled.Person)
}

val routes = listOf(
    Screen.Map,
    Screen.Profile,
)

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar(modifier = Modifier.height(NavigationBarHeight)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        routes.forEach { screen ->
            BottomNavigationItem(icon = { Icon(screen.icon, contentDescription = null) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                })
        }
    }
}
