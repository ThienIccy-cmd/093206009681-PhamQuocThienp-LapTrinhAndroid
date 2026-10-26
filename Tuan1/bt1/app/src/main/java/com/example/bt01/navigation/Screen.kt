package com.example.bt01.navigation

/**
 * Navigation destination targets in UTH Todo app.
 */
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    
    object Onboarding : Screen("onboarding/{step}") {
        fun createRoute(step: Int): String = "onboarding/$step"
    }

    object Home : Screen("home")
}
