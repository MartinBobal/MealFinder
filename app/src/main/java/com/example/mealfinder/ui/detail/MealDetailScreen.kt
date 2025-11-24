package com.example.mealfinder.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mealfinder.data.api.MealApiClient
import com.example.mealfinder.data.model.Meal
import com.example.mealfinder.data.repository.MealRepository
import com.example.mealfinder.ui.favorite.FavoriteViewModel

@Composable
fun MealDetailScreen(
    mealId: String,
    onBack: () -> Unit,
    favoriteViewModel: FavoriteViewModel
) {
    var meal by remember { mutableStateOf<Meal?>(null) }

    LaunchedEffect(mealId) {
        val repo = MealRepository(MealApiClient.api)
        meal = repo.getMealById(mealId).meals?.firstOrNull()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = onBack) {
            Text("Zpět")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (meal == null) {
            Text("Načítám…")
        } else {

            Image(
                painter = rememberAsyncImagePainter(meal!!.strMealThumb),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = meal!!.strMeal ?: "")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    favoriteViewModel.addFavorite(meal!!.strMeal ?: "Neznámý recept")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Přidat do oblíbených")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = meal!!.strInstructions ?: "")
        }
    }
}
