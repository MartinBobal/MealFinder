package com.example.mealfinder.ui.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    onMealClick: (String) -> Unit
) {
    val favorites by viewModel.favorites.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Oblíbené recepty") }) }
    ) { padding ->

        if (favorites.isEmpty()) {
            Box(Modifier.padding(padding).padding(16.dp)) {
                Text("Zatím nemáš žádné oblíbené recepty.")
            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                items(favorites) { item ->

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable { onMealClick(item.id) }
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(item.name)

                        Button(
                            onClick = { viewModel.removeFavorite(item.id) }
                        ) {
                            Text("X")
                        }
                    }

                    Divider()
                }
            }
        }
    }
}
