package com.example.greenscene.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.greenscene.bottomNavRoutes
import com.example.greenscene.ui.theme.NavigationBarHeight

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar(modifier = Modifier.height(NavigationBarHeight)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavRoutes.forEach { route ->
            BottomNavigationItem(icon = { Icon(route.icon, contentDescription = null) },
                selected = currentDestination?.hierarchy?.any { it.route == route.screen } == true,
                onClick = {
                    navController.navigate(route.screen) {
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