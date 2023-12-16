package com.example.greenscene

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector


const val SPLASH_SCREEN = "SplashScreen"
const val SIGN_UP_SCREEN = "SignUpScreen"
const val SIGN_IN_SCREEN = "SignInScreen"
const val LOG_IN_SCREEN = "LogInScreen"
const val MAP_SCREEN = "MapScreen"
const val PROFILE_SCREEN = "ProfileScreen"

sealed class BottomNavRoute(
    val screen: String, @StringRes val resourceId: Int, val icon: ImageVector
) {
    object Map : BottomNavRoute(MAP_SCREEN, R.string.map_route, Icons.Filled.LocationOn)
    object Profile : BottomNavRoute(PROFILE_SCREEN, R.string.profile_route, Icons.Filled.Person)
}

val bottomNavRoutes = listOf(
    BottomNavRoute.Map, BottomNavRoute.Profile
)