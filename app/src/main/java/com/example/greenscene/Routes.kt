package com.example.greenscene

import androidx.annotation.StringRes

const val SPLASH_SCREEN = "SplashScreen"
const val SIGN_UP_SCREEN = "SignUpScreen"
const val SIGN_IN_SCREEN = "SignInScreen"
const val LOG_IN_SCREEN = "LogInScreen"
const val MAP_SCREEN = "MapScreen"
const val PROFILE_SCREEN = "ProfileScreen"

sealed class BottomNavRoute(
    val screen: String, @StringRes val resourceId: Int, val iconId: Int
) {
    object Map : BottomNavRoute(MAP_SCREEN, R.string.map_route, R.drawable.location)
    object Profile : BottomNavRoute(PROFILE_SCREEN, R.string.profile_route, R.drawable.profile)
}

val bottomNavRoutes = listOf(
    BottomNavRoute.Map, BottomNavRoute.Profile
)