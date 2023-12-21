package com.example.greenscene.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.greenscene.bottomNavRoutes
import com.example.greenscene.ui.theme.GreenSceneTheme
import com.example.greenscene.ui.theme.NavigationBarHeight

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar(
        modifier = Modifier
            .height(NavigationBarHeight),
        containerColor = Color.Transparent
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavRoutes.forEach { route ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == route.screen } == true
            BottomNavigationItem(icon = {
                Icon(
                    painter = painterResource(id = route.iconId),
                    contentDescription = null,
                    tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
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

@Preview
@Composable
fun BottomBarPreview() {
    GreenSceneTheme(useDarkTheme = true) {
        BottomBar(navController = NavController(LocalContext.current))
    }
}