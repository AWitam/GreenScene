package com.example.greenscene

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

class GreenSceneAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val bottomBarState: MutableState<Boolean>,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {

    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}