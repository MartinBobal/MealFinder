package com.example.mealfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.mealfinder.data.api.MealApiClient
import com.example.mealfinder.data.repository.MealRepository
import com.example.mealfinder.navigation.AppNavHost
import com.example.mealfinder.ui.theme.MealFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create Repository (REST API)
        val repository = MealRepository(MealApiClient.api)

        setContent {
            MealFinderTheme {

                // Create navigation controller
                val navController = rememberNavController()

                // Show navigation graph
                AppNavHost(
                    navController = navController
                )
            }
        }
    }
}
