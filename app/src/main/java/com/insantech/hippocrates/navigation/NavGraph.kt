package com.insantech.hippocrates.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.insantech.hippocrates.ui.assessment.AssessmentScreen
import com.insantech.hippocrates.ui.result.ResultScreen
import com.insantech.hippocrates.ui.about.AboutScreen
import com.insantech.hippocrates.ui.home.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Assessment : Screen("assessment")
    object Result : Screen("result")
    object About : Screen("about")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Assessment.route) {
            AssessmentScreen(navController)
        }
        composable(Screen.Result.route) {
            ResultScreen(navController)
        }
        composable(Screen.About.route) {
            AboutScreen(navController)
        }
    }
}
