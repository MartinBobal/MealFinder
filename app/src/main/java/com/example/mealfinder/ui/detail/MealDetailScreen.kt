package com.example.mealfinder.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mealfinder.data.repository.MealRepository
import com.example.mealfinder.data.api.MealApiClient

@Composable
fun MealDetailScreen(
    mealId: String,
    onBack: () -> Unit
) {
    var mealState by remember { mutableStateOf<com.example.mealfinder.data.model.Meal?>(null) }

    // Načíst data při vstupu na obrazovku
    LaunchedEffect(mealId) {
        val repo = MealRepository(MealApiClient.api)
        val meal = repo.getMealById(mealId).meals?.firstOrNull()
        mealState = meal
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = onBack) {
            Text("Zpět")
        }

        Spacer(modifier = Modifier.height(16.dp))

        val meal = mealState
        if (meal == null) {
            Text("Načítám…")
        } else {
            Image(
                painter = rememberAsyncImagePainter(meal.strMealThumb),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = meal.strMeal ?: "")

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = meal.strInstructions ?: "")
        }
    }
}
