package com.example.mealfinder.ui.random

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mealfinder.ui.favorite.FavoriteViewModel
import kotlinx.coroutines.launch

@Composable
fun RandomMealScreen(
    viewModel: RandomMealViewModel,
    favoriteViewModel: FavoriteViewModel,
    onBack: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.loadRandomMeal()
    }

    val meal = viewModel.meal.value
    val mealId = meal?.idMeal ?: ""
    val isFavorite by favoriteViewModel.isFavorite(mealId).collectAsState()

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

            Spacer(modifier = Modifier.height(16.dp))

            if (meal == null) {
                Text("Načítám...")
            } else {

                Image(
                    painter = rememberAsyncImagePainter(meal.strMealThumb),
                    contentDescription = meal.strMeal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = meal.strMeal ?: "")

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (isFavorite) {
                            favoriteViewModel.removeFavorite(mealId)
                            scope.launch {
                                snackbarHostState.showSnackbar("Odebráno z oblíbených")
                            }
                        } else {
                            favoriteViewModel.addFavorite(mealId, meal.strMeal ?: "")
                            scope.launch {
                                snackbarHostState.showSnackbar("Přidáno do oblíbených")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isFavorite) "Odebrat z oblíbených" else "Přidat do oblíbených")
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = meal.strInstructions ?: "")

                Spacer(modifier = Modifier.height(24.dp))

                Button(onClick = { viewModel.loadRandomMeal() }) {
                    Text("Další náhodný recept")
                }
            }
        }
    }
}
