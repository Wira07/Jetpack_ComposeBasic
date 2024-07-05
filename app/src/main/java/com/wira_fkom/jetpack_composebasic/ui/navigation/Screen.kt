package com.wira_fkom.jetpack_composebasic.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
}
