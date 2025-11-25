package com.example.mealfinder.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onMealClick: (String) -> Unit,
    onBack: () -> Unit
) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Hledat jídlo...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { viewModel.search(query) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Vyhledat")
        }

        Spacer(Modifier.height(16.dp))

        val results = viewModel.results.collectAsState()

        LazyColumn {
            items(results.value) { meal ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMealClick(meal.idMeal ?: "") }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(meal.strMealThumb),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(end = 8.dp)
                    )

                    Text(meal.strMeal ?: "")
                }
            }
        }
    }
}
