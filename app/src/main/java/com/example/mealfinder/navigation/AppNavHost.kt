package com.example.mealfinder.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
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
fun AppNavHost(
    navController: NavHostController,
    favoriteViewModel: FavoriteViewModel,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = "welcome",
        modifier = Modifier.padding(padding)
    ) {

        composable("welcome") {
            WelcomeScreen(
                navController = navController,
                onRandomClick = { navController.navigate("random") },
                onSearchClick = { navController.navigate("search") }
            )
        }

        composable("random") {
            val vm: RandomMealViewModel = viewModel(
                factory = RandomMealViewModelFactory(
                    MealRepository(MealApiClient.api)
                )
            )

            RandomMealScreen(
                viewModel = vm,
                favoriteViewModel = favoriteViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable("search") {
            val vm: SearchViewModel = viewModel(
                factory = SearchViewModelFactory(
                    MealRepository(MealApiClient.api)
                )
            )

            SearchScreen(
                viewModel = vm,
                onMealClick = { id -> navController.navigate("detail/$id") },
                onBack = { navController.popBackStack() }
            )
        }

        composable("detail/{mealId}") { entry ->
            val mealId = entry.arguments?.getString("mealId") ?: ""

            MealDetailScreen(
                mealId = mealId,
                favoriteViewModel = favoriteViewModel,
                onBack = { navController.popBackStack() }
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
