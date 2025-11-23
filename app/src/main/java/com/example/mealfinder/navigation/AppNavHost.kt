package com.example.mealfinder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {

        composable("welcome") {
            // TODO: WelcomeScreen
        }

        composable("random") {
            // TODO: RandomMealScreen
        }

        composable("search") {
            // TODO: SearchScreen
        }

        composable("detail/{mealId}") { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
            // TODO: MealDetailScreen
        }
    }
}
