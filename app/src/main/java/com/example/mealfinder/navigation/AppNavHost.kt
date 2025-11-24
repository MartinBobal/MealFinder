package com.example.mealfinder.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mealfinder.data.api.MealApiClient
import com.example.mealfinder.data.repository.MealRepository
import com.example.mealfinder.ui.detail.MealDetailScreen
import com.example.mealfinder.ui.favorite.FavoriteScreen
import com.example.mealfinder.ui.favorite.FavoriteViewModel
import com.example.mealfinder.ui.random.RandomMealScreen
import com.example.mealfinder.ui.random.RandomMealViewModel
import com.example.mealfinder.ui.random.RandomMealViewModelFactory
import com.example.mealfinder.ui.search.SearchScreen
import com.example.mealfinder.ui.search.SearchViewModel
import com.example.mealfinder.ui.search.SearchViewModelFactory
import com.example.mealfinder.ui.welcome.WelcomeScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    // 🔥 Sdílený favorit ViewModel pro celou aplikaci
    val favoriteViewModel: FavoriteViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {

        composable("welcome") {
            WelcomeScreen(
                navController = navController,
                onRandomClick = { navController.navigate("random") },
                onSearchClick = { navController.navigate("search") }
            )
        }

        composable("random") {
            val viewModel: RandomMealViewModel = viewModel(
                factory = RandomMealViewModelFactory(
                    MealRepository(MealApiClient.api)
                )
            )

            RandomMealScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable("search") {
            val viewModel: SearchViewModel = viewModel(
                factory = SearchViewModelFactory(
                    MealRepository(MealApiClient.api)
                )
            )

            SearchScreen(
                viewModel = viewModel,
                onMealClick = { mealId ->
                    navController.navigate("detail/$mealId")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("detail/{mealId}") { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""

            MealDetailScreen(
                mealId = mealId,
                onBack = { navController.popBackStack() },
                favoriteViewModel = favoriteViewModel
            )
        }

        composable("favorite") {
            FavoriteScreen(
                favoritesViewModel = favoriteViewModel,
                onMealClick = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }
    }
}
