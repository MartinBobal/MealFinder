package com.example.mealfinder.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
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
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var meal by remember { mutableStateOf<Meal?>(null) }

    val isFavorite by favoriteViewModel
        .isFavorite(mealId)
        .collectAsState(initial = false)

    LaunchedEffect(mealId) {
        val repo = MealRepository(MealApiClient.api)
        meal = repo.getMealById(mealId).meals?.firstOrNull()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = onBack) { Text("Zpět") }

            Spacer(Modifier.height(16.dp))

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

                Spacer(Modifier.height(16.dp))

                Text(meal!!.strMeal ?: "")

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (isFavorite) {
                            favoriteViewModel.removeFavorite(mealId)
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Recept odebrán z oblíbených")
                            }
                        } else {
                            favoriteViewModel.addFavorite(
                                id = mealId,
                                name = meal!!.strMeal ?: ""
                            )
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Recept přidán do oblíbených")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (isFavorite) "Odebrat z oblíbených"
                        else "Přidat do oblíbených"
                    )
                }

                Spacer(Modifier.height(24.dp))

                Text(meal!!.strInstructions ?: "")
            }
        }
    }
}
