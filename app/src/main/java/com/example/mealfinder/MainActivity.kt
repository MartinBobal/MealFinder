package com.example.mealfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mealfinder.navigation.AppNavHost
import com.example.mealfinder.navigation.BottomNavBar
import com.example.mealfinder.ui.favorite.FavoriteViewModel
import com.example.mealfinder.ui.theme.MealFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MealFinderTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val favoriteViewModel: FavoriteViewModel = viewModel()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            favoriteViewModel = favoriteViewModel,
            padding = innerPadding
        )
    }
}
