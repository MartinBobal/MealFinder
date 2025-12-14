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
    // Lokální stav pro text vyhledávacího dotazu
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        // Textové pole pro zadání vyhledávacího dotazu
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Hledat jídlo...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        // Spuštění vyhledávání přes ViewModel
        Button(
            onClick = { viewModel.search(query) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Vyhledat")
        }

        Spacer(Modifier.height(16.dp))

        // Sledování výsledků vyhledávání ze StateFlow
        val results = viewModel.results.collectAsState()

        // Seznam nalezených receptů
        LazyColumn {
            items(results.value) { meal ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMealClick(meal.idMeal ?: "") }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Náhledový obrázek receptu
                    Image(
                        painter = rememberAsyncImagePainter(meal.strMealThumb),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(end = 8.dp)
                    )

                    // Název receptu
                    Text(meal.strMeal ?: "")
                }
            }
        }
    }
}