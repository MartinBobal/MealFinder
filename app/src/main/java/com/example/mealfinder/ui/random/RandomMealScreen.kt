package com.example.mealfinder.ui.random

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun RandomMealScreen(
    viewModel: RandomMealViewModel,
    onBack: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.loadRandomMeal()
    }

    val meal = viewModel.meal.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = onBack) {
            Text("Zpět")
        }

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

            Text(text = meal.strInstructions ?: "")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { viewModel.loadRandomMeal() }) {
                Text("Další recept")
            }
        }
    }
}
