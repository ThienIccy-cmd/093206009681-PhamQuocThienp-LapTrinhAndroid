package com.example.bt01.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bt01.ui.screens.HomeScreen
import com.example.bt01.ui.screens.OnboardingScreen
import com.example.bt01.ui.screens.SplashScreen

/**
 * App Navigation Host using Jetpack Navigation Compose.
 */
@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // 1. Splash Screen
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateNext = {
                    navController.navigate(Screen.Onboarding.createRoute(1)) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // 2. Onboarding Flow (steps 1, 2, 3)
        composable(
            route = Screen.Onboarding.route,
            arguments = listOf(navArgument("step") { type = NavType.IntType })
        ) { backStackEntry ->
            val step = backStackEntry.arguments?.getInt("step") ?: 1

            OnboardingScreen(
                stepNumber = step,
                onNextStep = { nextStep ->
                    navController.navigate(Screen.Onboarding.createRoute(nextStep))
                },
                onFinishOnboarding = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        // 3. Main Home Screen
        composable(Screen.Home.route) {
            HomeScreen(
                onRestartOnboarding = {
                    navController.navigate(Screen.Onboarding.createRoute(1)) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
